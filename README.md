# Final Minecraft

## Final Minecraft is a [Fabric mod](https://www.fabricmc.net/) for [Minecraft](https://www.minecraft.net/en-us) 1.20.1

# Features
Final Minecraft (FMC) adds a variety of Final Fantasy inpsired content to Minecraft.

FMC is designed to augment surival mode with new combat, building, and crafting gameplay. Added content is centred around balance and longevity, allowing players to form new long-term goals in the sandbox environment.

### Current features
* [Elemental Aspects](#)
* [Damage Numbers](#)
* [Calendar System](#)
* [Crystals](#)
* ~~Status effects~~
* ~~Jobs~~

For detailed information about the mod, [visit the wiki](#).

You can also [join the official Final Minecraft Discord](#).

### Supported Versions
As the mod is still in alpha, there is no release.

**Version Info**
* Release date: `00/00/20233`
* FMC Version: `0.1.0 "Biggs"`
* For Minecraft Java: `1.20.1 "Something Something Update"`
* [Changelist](wiki/Version-History)

Final Minecraft is a **Fabric Mod** and is developed exclusively for the Fabric mod loader. The LGPL license means that you are free to port FMC to another mod loader, such as Forge.

Official builds can be found on Modrinth, or the github repo.

Development will always be targeted at the latest stable release of Minecraft. Older versions will not be supported.

# Client / Server
FMC should be installed on both clients and the server.

# Dependencies

|                    Name | Version | CurseForge Page   | GitHub Repository | Client/Server |
|------------------------:|:-------:|:-----------------:|:-----------------:|:-------------:|
|              Fabric API | 0.84.0+ | [link][fabric_MR] | [link][fabric_GH] | **Both**      |

Still debating whether to bundle core dependencies.

# Optional Integrations
FMC features built-in integration for these mods, but they are not required:

|                    Name | Version | Modrinth Page     | GitHub Repository | Client/Server |
|------------------------:|:-------:|:-----------------:|:-----------------:|:-------------:|
|                Mod Menu | 7.1.0   | [link][mm_MR]     | [link][mm_GH]     | **Client**    |

# Add-ons

This is the core module for Final Minecraft. Some optional features (such as HUD overhaul) are planned as self-contained mods for compatibility reasons.

# For Developers

## Build Instructions
Clone the repo and run:

```bash
./gradlew build
```

You can find the compiled jar at: `build/libs/fmc-0.1.0.jar`

## Creating Add-ons
FMC exposes an API for mod developers to create their own FMC add-ons.

* Since the mod is still in alpha and the code is likely to change signifanctly, this section is just a placeholder

*TODO: modImplementation instructions*
*TODO: setup maven on repo*


## External Mod Support
FMC utilises the Tag system in Minecraft. Where possible, every feature uses the tags registry, meaning that the mod is fully compatible with other mods!

See the [wiki Datapacks page](#) for details on how use Tags used by FMC.

This repository also contains a tool written in Lua that helps to generate tag json from a CSV file (such as that exported from a spreasheet), run `genTags.lua -h` for a list of options. See the [lua/genTags.lua](lua/genTags.lua) file for more information.

# Authors

Lafolie - designer and developer.

## Special Thanks
* To Linguardium of the Fabric discord, for his tremendous help in learning how to do this stuff!
* To Papierkorb2292 for the guidance in Minecraft's rendering system
* To the creators of Cardinal Components for saving me a tonne of time replicating data

## License

FMC is licensed under the GNU Lesser General Public License v3.

[fabric_MR]: https://modrinth.com/mod/fabric-api
[fabric_GH]: https://github.com/FabricMC/fabric
[mm_MR]: https://modrinth.com/mod/modmenu
[mm_GH]: https://github.com/TerraformersMC/ModMenu