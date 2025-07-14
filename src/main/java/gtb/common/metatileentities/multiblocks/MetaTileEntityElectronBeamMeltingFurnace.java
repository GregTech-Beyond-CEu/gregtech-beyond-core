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
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

public class MetaTileEntityElectronBeamMeltingFurnace extends RecipeMapMultiblockController {

    public MetaTileEntityElectronBeamMeltingFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.ELECTRON_BEAM_MELTING_FURNACE_RECIPES);
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                .aisle("CGGGGGC~~~~~~~~~~", "CGGGGGC~~UU~~~~UU", "CGGGGGC~~UUPPPPUU", "CGGGGGC~~UU~~~~UU", "CCCSCCC~~~~~~~~~~")
                .aisle("CGGGGGC~~UU~~~~UU", "CHHHHHC~~UU~~~~UU", "CHHHHHC~~UU~~~~UU", "CHHHHHC~~UU~~~~UU", "CCCCCCC~~UU~~~~UU")
                .aisle("CGGGGGC~~UUPPPPUU", "CHHHHHC~ZUU~~~~UU", "C~~~~~CPPUU~~~~UU", "C~~~~~C~ZUU~~~~UU", "CCCCCCC~~UUPPPPUU")
                .aisle("CGGGGGC~~UU~~~~UU", "CHHHHHC~~UU~~~~UU", "CHHHHHC~~UU~~~~UU", "CHHHHHC~~UU~~~~UU", "CCCCCCC~~UU~~~~UU")
                .aisle("CGGGGGC~~~~~~~~~~", "CGGGGGC~~UU~~~~UU", "CGGGGGC~~UUPPPPUU", "CGGGGGC~~UU~~~~UU", "CCCCCCC~~~~~~~~~~")
                .where('S', selfPredicate())
                .where('~', any())
                .where('C', states(getCasingState()).setMinGlobalLimited(4)
                        .or(autoAbilities()))
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)))
                .where('H', states(MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.RTM_ALLOY)))
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)))
                .where('Z', states(MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX)))
                .where('U', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID)))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.HEAT_PROOF_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.ASSEMBLER_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityElectronBeamMeltingFurnace(metaTileEntityId);
    }
}
