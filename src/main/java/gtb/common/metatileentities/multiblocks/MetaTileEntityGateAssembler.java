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
import gtb.api.recipes.GTBRecipeMaps;
import gtb.common.block.GTBMetaBlocks;
import gtb.common.block.blocks.GTBMultiblockCasing;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import static gregtech.api.unification.material.Materials.Titanium;

public class MetaTileEntityGateAssembler extends RecipeMapMultiblockController {

    public MetaTileEntityGateAssembler(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.GATE_ASSEMBLER_RECIPES);
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                .aisle("~~CCC~~", "F~TTT~F", "~~TTT~~", "~~TTT~~", "~~TTT~~", "~~TTT~~", "~~TTT~~", "F~TTT~F", "~~TUT~~", "~~CCC~~")
                .aisle("~CRRRC~", "FT~~~TF", "~G~~~G~", "~G~~~G~", "~G~~~G~", "~G~~~G~", "~G~~~G~", "FT~~~TF", "~T~~~T~", "~CRRRC~")
                .aisle("~CRRRC~", "FT~X~TF", "~G~X~G~", "~G~X~G~", "~G~X~G~", "~G~X~G~", "~G~X~G~", "FT~X~TF", "~U~X~U~", "~CRSRC~")
                .aisle("~CRRRC~", "FT~~~TF", "~G~~~G~", "~G~~~G~", "~G~~~G~", "~G~~~G~", "~G~~~G~", "FT~~~TF", "~T~~~T~", "~CRRRC~")
                .aisle("~~CCC~~", "~~TUT~~", "~FTTTF~", "~~GGG~~", "~~GGG~~", "~~GGG~~", "~~GGG~~", "~FTTTF~", "~~TUT~~", "~~CCC~~")
                .aisle("~~~~~~~", "~~~~~~~", "~~FFF~~", "~~~F~~~", "~~~F~~~", "~~~F~~~", "~~~F~~~", "~~FFF~~", "~~~~~~~", "~~~~~~~")
                .where('S', selfPredicate())
                .where('~', any())
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)))
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)))
                .where('C', states(getCasingState()).setMinGlobalLimited(5)
                        .or(autoAbilities()))
                .where('U', states(MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX)))
                .where('T', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE)))
                .where('X', states(MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLY_LINE_CASING)))
                .where('F', frames(Titanium))
                .where('R', states(MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLY_CONTROL)))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, false, true, true, true, true, true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.SOLID_STEEL_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.ASSEMBLER_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityGateAssembler(metaTileEntityId);
    }
}
