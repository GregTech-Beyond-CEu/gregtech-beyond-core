### GREGTECH BEYOND:

where to find the project and follow its development?
curseforge:
https://www.curseforge.com/minecraft/modpacks/gregtech-beyond
discord:
https://discord.gg/sG6NZ7NaeC
other Projects
https://github.com/Gregtech-Beyond

### INSTALLATION:

Run `./gradlew setupDecompWorkspace`.

### Build:

Run `./gradlew :spotlessApply build`.

### Change GTCEu gradle dependency version:

- Edit the line containing `curse.maven:gregtech-ce-unofficial` in [dependencies.gradle](dependencies.gradle).
- Reload all gradle projects 

### For Developers:

change the Run Config of Supercrtical
run\config\supercritical.cfg

        # Remove all materials from Supercritical, except Corium.
        # Default: false
        B:disableAllMaterials=true

        # Remove all recipes from Supercritical.
        # Default: false
        B:disableAllRecipes=true

        # Whether to register heat exchanger or not. Useful for SuSy.
        # Default: true
        B:enableHX=true

        # Do material modifications, like adding flags or properties.
        # Modpack devs might want to disable this to reduce conflicts.
        # Default: true
        B:enableMaterialModifications=false

Credits:

Gregicality Legacy (GCYL)
for textures and their corresponding recipes.

GT6 and Greg for his great mods and ideas.

Supersymmetry (SUSY)
for their Idea of Catalytic Reformation Unit and its corresponding recipes
for some of their ideas and lines

[InventoryBogoSorter](https://github.com/CleanroomMC/InventoryBogoSorter) and [Supercritical](https://github.com/SymmetricDevs/Supercritical)  for MixinLoader template and examples





