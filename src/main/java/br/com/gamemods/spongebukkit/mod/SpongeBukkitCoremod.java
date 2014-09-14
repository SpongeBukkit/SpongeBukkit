package br.com.gamemods.spongebukkit.mod;

import cpw.mods.fml.relauncher.IFMLCallHook;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.bukkit.entity.Entity;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Set;

@IFMLLoadingPlugin.MCVersion("1.7.10")
public class SpongeBukkitCoremod implements IFMLLoadingPlugin, IClassTransformer, IFMLCallHook
{
    /**
     * The class(es) that do(es) the transforming. Needs to implement IClassTransformer in some way
     */
    @Override
    public String[] getASMTransformerClass()
    {
        // Call order: 2 (2 times)
        System.out.println("*********** SpongeBukkitCoremod is being loaded ***********");
        return new String[]{
                "br.com.gamemods.spongebukkit.mod.SpongeBukkitCoremod"
        };
    }

    /**
     * The class that acts similarly to the @Mod annotation.
     */
    @Override
    public String getModContainerClass()
    {
        //return "br.com.gamemods.spongebukkit.mod.SpongeBukkitMod";
        return null;
    }

    /**
     * If you want to do stuff BEFORE minecraft starts, but after your mod is loaded.
     * @return
     */
    @Override
    public String getSetupClass()
    {
        // Call order: 4
        System.out.println("*********** SpongeBukkitCoremod.getSetupClass: ClassLoader: "+getClass().getClassLoader()+" ***********");
        LaunchClassLoader classLoader = (LaunchClassLoader) getClass().getClassLoader();
        try
        {
            System.out.println("*********** SpongeBukkitCoremod.getSetupClass: Allowing classes inside org.apache to be loaded from custom jars ***********");
            Field fd = LaunchClassLoader.class.getDeclaredField("classLoaderExceptions");
            fd.setAccessible(true);
            Set<String> set = (Set<String>) fd.get(classLoader);
            set.remove("org.apache.");

            System.out.println("*********** SpongeBukkitCoremod.getSetupClass: Adding org.apache transformer exceptions ***********");
            fd = LaunchClassLoader.class.getDeclaredField("transformerExceptions");
            fd.setAccessible(true);
            set = (Set<String>) fd.get(classLoader);
            set.add("org.apache.");

            /*fd = classLoader.getClass().getDeclaredField("transformers");
            fd.setAccessible(true);
            List<IClassTransformer> transformers = (List<IClassTransformer>) fd.get(classLoader);
            transformers.add(0, new IClassTransformer()
            {
                @Override
                public byte[] transform(String s, String s2, byte[] bytes)
                {
                    if(!s2.startsWith("net.minecraft") && !s2.startsWith("cpw."));
                        System.out.println("Class Transformer: "+s+", "+s2+", "+bytes.length);
                    return bytes;
                }
            });

            System.out.println("invalidClasses");
            fd = classLoader.getClass().getDeclaredField("invalidClasses");
            fd.setAccessible(true);
            System.out.println(fd.get(classLoader));

            System.out.println("classLoaderExceptions");
            fd = classLoader.getClass().getDeclaredField("classLoaderExceptions");
            fd.setAccessible(true);
            System.out.println(fd.get(classLoader));

            System.out.println("transformerExceptions");
            fd = classLoader.getClass().getDeclaredField("transformerExceptions");
            fd.setAccessible(true);
            System.out.println(fd.get(classLoader));

            System.out.println("packageManifests");
            fd = classLoader.getClass().getDeclaredField("packageManifests");
            fd.setAccessible(true);
            System.out.println(fd.get(classLoader));*/
        }
        catch (ReflectiveOperationException | NullPointerException e)
        {
            e.printStackTrace();
        }
        File libs = new File("bukkit-libs");
        if(!libs.isDirectory())
            libs.mkdirs();
        for(File file: libs.listFiles())
        {
            if(file.isFile() && file.getName().endsWith(".jar"))
            {
                try
                {
                    System.out.println("*********** SpongeBukkitCoremod.getSetupClass: Injecting "+file+" into class loader! ***********");
                    classLoader.addURL(file.toPath().toUri().toURL());
                }
                catch (MalformedURLException e)
                {
                    e.printStackTrace();
                }
            }
        }

        Class<?> validateClass = null;
        try
        {
            Class.forName("org.apache.commons.lang.Validate", false, classLoader);
        }
        catch (ReflectiveOperationException e)
        {
            System.err.println("*********** SpongeBukkitCoremod.getSetupClass: Apache Commons Lang v2 is NOT present! Err: "+e+" ***********");
        }
        return null;
    }

    /**
     * Gives the mod coremod data if it wants it.
     */
    @Override
    public void injectData(Map<String, Object> data)
    {
        // Call order: 3
        System.out.println("*********** SpongeBukkitCoremod.injectData("+data+") *********** ");
    }

    @Override
    public String getAccessTransformerClass()
    {
        // Call order: 1
        System.out.println("*********** SpongeBukkitCoremod.getAccessTransformerClass() ClassLoader: "+getClass().getClassLoader()+" *********** ");
        return "br.com.gamemods.spongebukkit.mod.SpongeBukkitAccessTransformer";
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes)
    {
        //System.out.println("*********** transform("+name+", "+transformedName+", "+bytes.length+" bytes) *********** ");
        if("net.minecraft.entity.Entity".equals(transformedName))
        {
            System.out.println("*********** SpongeBukkitCoremod.transform is patching net.minecraft.entity.Entity ***********");
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(bytes);
            classReader.accept(classNode, 0);

            classNode.fields.add(new FieldNode(Opcodes.ACC_PUBLIC | Opcodes.ACC_TRANSIENT, "_sb_bukkit_entity", Type.getDescriptor(Entity.class), null, null));

            ClassWriter writer = new ClassWriter(0);
            classNode.accept(writer);
            return writer.toByteArray();
        }
        else if(name.startsWith("org.apache.commons.lang."))
        {
            System.out.println("*********** SpongeBukkitCoremod.transform("+name+", "+transformedName+", "+bytes.length+" bytes) [doing nothing, only debugging] *********** ");
        }

        return bytes;
    }

    @Override
    public Void call() throws Exception
    {
        System.out.println("*********** SpongeBukkitCoremod.call() *********** ");
        return null;
    }
}
