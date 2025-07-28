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

public class MetaTileEntityRegenerationUnit extends RecipeMapMultiblockController {

    public MetaTileEntityRegenerationUnit(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.REGENERATION_UNIT_RECIPES);
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                .aisle("C~~CCC~", "~~CCCCC", "C~CCCCC", "~~CCCCC", "C~~CSC~")
                .aisle("E~~CCC~", "~~C~~~C", "E~C~~~C", "~~C~~~C", "E~~CCC~")
                .aisle("P~~CCC~", "~~C~~~C", "P~C~~~C", "~~C~~~C", "P~~CCC~")
                .aisle("P~~CCC~", "~~C~~~C", "P~C~~~C", "~~C~~~C", "P~~CCC~")
                .aisle("Z~~CCC~", "~~C~~~C", "Z~C~~~C", "~~C~~~C", "Z~~CCC~")
                .aisle("P~~CZC~", "~~C~~~C", "P~C~~~C", "~~C~~~C", "P~~CZC~")
                .aisle("PPPP~~~", "~~~CCC~", "PPPCCC~", "~~~CCC~", "PPPP~~~")
                .where('S', selfPredicate())
                .where('C', states(getCasingState()).setMinGlobalLimited(30)
                        .or(autoAbilities()))
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE)))
                .where('Z', states(MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TITANIUM_GEARBOX)))
                .where('E', states(MetaBlocks.BOILER_FIREBOX_CASING.getState(BlockFireboxCasing.FireboxCasingType.TITANIUM_FIREBOX)))
                .where('~', any())
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.STABLE_TITANIUM_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.ASSEMBLER_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityRegenerationUnit(metaTileEntityId);
    }
}
