package gtb.common.metatileentities.multiblocks;

import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.RelativeDirection;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.*;
import gtb.api.recipes.GTBRecipeMaps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import static gregtech.common.blocks.BlockBoilerCasing.BoilerCasingType.STEEL_PIPE;
import static gregtech.common.blocks.BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE;

public class MetaTileEntityComponentAssembler extends RecipeMapMultiblockController {

    public MetaTileEntityComponentAssembler(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.COMPONENT_ASSEMBLER_RECIPES);
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.DOWN)
                .aisle("~CCC~", "~CCC~", "~CCC~", "~CCC~", "~CCC~", "~FFN~", "~~~N~", "~~~N~", "~~~N~", "~FFN~", "~CCC~", "~JCJ~", "~JCJ~", "~JCJ~", "~JCJ~", "~JCJ~", "~JCJ~", "~JCJ~")
                .aisle("CCCCC", "C~~~C", "C~B~C", "C~~~C", "CCCCC", "F~N~N", "F~N~N", "F~N~N", "F~N~N", "F~N~N", "CCCCC", "C~~~C", "C~~~C", "C~~~C", "C~~~C", "C~~~C", "C~~~C", "CCCCC")
                .aisle("CCCCC", "G~~~G", "G~B~G", "G~~~G", "CCCCC", "F~~NF", "~~~N~", "~~~N~", "~~~N~", "F~~NF", "CCCCC", "G~~~G", "G~~~G", "G~~~G", "G~~~G", "G~~~G", "G~~~G", "CCCCC")
                .aisle("CCCCC", "G~~~G", "G~B~G", "G~~~G", "CCCCC", "FN~~F", "~N~~~", "~N~~~", "~N~~~", "FN~~F", "CCCCC", "G~P~G", "G~P~G", "G~Z~G", "G~P~G", "G~Z~G", "G~P~G", "CCCCC")
                .aisle("CCCCC", "G~~~G", "G~B~G", "G~~~G", "CCCCC", "N~N~F", "N~N~~", "N~N~~", "N~N~~", "N~N~F", "CCCCC", "G~~~G", "G~~~G", "G~~~G", "G~~~G", "G~~~G", "G~~~G", "CCSCC")
                .aisle("CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC", "FN~~F", "FN~~F", "FN~~F", "FN~~F", "FN~~F", "CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC")
                .where('S', selfPredicate())
                .where('G',
                        states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)))
                .where('~', any())
                .where('C', states(getCasingState()).setMinGlobalLimited(40)
                        .or(autoAbilities()))
                .where('N', states(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.CUTTER_CASING)))
                .where('F', frames(Materials.HSSS))
                .where('B', states(MetaBlocks.BOILER_CASING.getState(TUNGSTENSTEEL_PIPE)))
                .where('P', states(MetaBlocks.BOILER_CASING.getState(STEEL_PIPE)))
                .where('Z', states(MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLY_CONTROL)))
                .where('J', states(MetaBlocks.BOILER_FIREBOX_CASING.getState(BlockFireboxCasing.FireboxCasingType.TUNGSTENSTEEL_FIREBOX)))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, false);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.ROBUST_TUNGSTENSTEEL_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.ASSEMBLER_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityComponentAssembler(metaTileEntityId);
    }
}
