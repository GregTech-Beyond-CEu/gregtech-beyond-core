package gtb.common.metatileentities.multiblocks;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.RelativeDirection;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.*;
import gtb.common.block.GTBMetaBlocks;
import gtb.common.block.blocks.GTBMultiblockCasing2;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import static gtb.api.recipes.GTBRecipeMaps.*;

public class MetaTileEntityVacuumArcRemeltingFurnace extends RecipeMapMultiblockController {

    public MetaTileEntityVacuumArcRemeltingFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, VAR_FURNACE_RECIPES);
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                .aisle( "~CCC~",
                        "CCCCC",
                        "CCCCC",
                        "CCCCC",
                        "~CCC~")

                .aisle( "~CCC~",
                        "CZ~ZC",
                        "C~~~C",
                        "C~Z~C",
                        "~CSC~")

                .aisle( "~GGG~",
                        "GV~VG",
                        "G~~~G",
                        "G~V~G",
                        "~GGG~")

                .aisle( "~GGG~",
                        "GV~VG",
                        "G~~~G",
                        "G~V~G",
                        "~GGG~")

                .aisle( "~CGC~",
                        "CV~VC",
                        "G~~~G",
                        "C~V~C",
                        "~CGC~")

                .aisle( "~~C~~",
                        "~CCC~",
                        "CCCCC",
                        "~CCC~",
                        "~~C~~")
                .where('S', selfPredicate())
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)))
                .where('Z', states(GTBMetaBlocks.GTB_MULTIBLOCK_CASING2.getState(GTBMultiblockCasing2.CasingType.WATER_COOLED_COPPER_MOLD)))
                .where('V', states(GTBMetaBlocks.GTB_MULTIBLOCK_CASING2.getState(GTBMultiblockCasing2.CasingType.ARC_FURNACE_ELECTRODE_BLOCK)))
                .where('~', any())
                .where('C', states(getCasingState()).setMinGlobalLimited(10).or(autoAbilities()))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, false);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.CLEAN_STAINLESS_STEEL_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.ASSEMBLER_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityVacuumArcRemeltingFurnace(metaTileEntityId);
    }
}
