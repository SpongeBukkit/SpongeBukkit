package br.com.gamemods.spongebukkit.mod;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import net.minecraft.launchwrapper.IClassTransformer;
import org.bukkit.entity.Entity;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import java.util.Map;

public class SpongeBukkitCoremod implements IFMLLoadingPlugin, IClassTransformer
{
    @Override
    public String[] getASMTransformerClass()
    {
        return new String[]{
                "net.minecraft.entity.Entity"
        };
    }

    @Override
    public String getModContainerClass()
    {
        return "br.com.gamemods.spongebukkit.mod.SpongeBukkitMod";
    }

    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data)
    {

    }

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes)
    {
        if("net.minecraft.entity.Entity".equals(name))
        {
            System.out.println("[SpongeBukkit Core] Patching net.minecraft.entity.Entity");
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(bytes);
            classReader.accept(classNode, 0);

            classNode.fields.add(new FieldNode(Opcodes.ACC_PUBLIC | Opcodes.ACC_TRANSIENT, "_sb_bukkit_entity", Type.getDescriptor(Entity.class), null, null));

            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            classNode.accept(writer);
            return writer.toByteArray();
        }

        return bytes;
    }
}
