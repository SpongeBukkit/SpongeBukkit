SpongeBukkit
============

Makes [SpongePowered](https://github.com/SpongePowered/Sponge) support [Bukkit](https://github.com/Bukkit/Bukkit) plugins (that doesn't requires net.minecraft.server and org.bukkit.craftbukkit packages from the old [CraftBukkit](https://github.com/Bukkit/CraftBukkit))

Compiling
---------

First, be sure to initialize the Git submodules:

    git submodule update --init --recursive

Now, use maven to build the bukkit module:

    cd bukkit
    mvn clean install


Then use the provided Gradle runtime to compile:

    ./gradlew build
  
**NOTE: YOU MAY NEED TO INSTALL THE LATEST [SpongePowered](https://github.com/SpongePowered/Sponge) AND [MinecraftForge](https://github.com/MinecraftForge/MinecraftForge) INTO YOUR LOCAL MAVEN REPOSITORY.**
