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
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gtb.api.recipes.GTBRecipeMaps;
import gtb.api.render.GTBTextures;
import gtb.common.block.GTBMetaBlocks;
import gtb.common.block.blocks.GTBMultiblockCasing2;
import gtb.common.block.blocks.GTBMultiblockCasing3;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import static gregtech.api.unification.material.Materials.StainlessSteel;
import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntityBridgmanFurnace extends RecipeMapMultiblockController {

    public MetaTileEntityBridgmanFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.BRIDGMAN_FURNACE_RECIPES);
    }

    public IBlockState getCasingState() {
        return GTBMetaBlocks.GTB_MULTIBLOCK_CASING3.getState(GTBMultiblockCasing3.CasingType.BRIDGMAN_FURNACE_CASING);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                .aisle("~FFF~", "F~~~F", "F~~~F", "F~~~F", "~FFF~")
                .aisle("~F~F~", "F~~~F", "~~~~~", "F~~~F", "~F~F~")
                .aisle("~F~F~", "F~~~F", "~~~~~", "F~~~F", "~F~F~")
                .aisle("~FFF~", "F~~~F", "F~~~F", "F~~~F", "~FFF~")
                .aisle("~PPP~", "PZZZP", "PZZZP", "PZZZP", "~PPP~")
                .aisle("~CCC~", "C~~~C", "C~~~C", "C~~~C", "~CCC~")
                .aisle("~CCC~", "C~~~C", "C~~~C", "C~~~C", "~CSC~")
                .aisle("~CCC~", "C~~~C", "C~~~C", "C~~~C", "~CCC~")
                .aisle("~CCC~", "C~~~C", "C~~~C", "C~~~C", "~CCC~")
                .aisle("~CCC~", "C~~~C", "C~~~C", "C~~~C", "~CCC~")
                .aisle("~CCC~", "C~~~C", "C~~~C", "C~~~C", "~CCC~")
                .aisle("~CCC~", "C~~~C", "C~~~C", "C~~~C", "~CCC~")
                .aisle("~CCC~", "C~~~C", "C~~~C", "C~~~C", "~CCC~")
                .aisle("~CCC~", "C~~~C", "C~~~C", "C~~~C", "~CCC~")
                .aisle("~~~~~", "~CCC~", "~CCC~", "~CCC~", "~~~~~")
                .where('S', selfPredicate())
                .where('~', any())
                .where('C', states(getCasingState()).setMinGlobalLimited(90)
                        .or(autoAbilities()))
                .where('F', frames(Steel))
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)))
                .where('Z', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID)))
                .build();
    }

    @Override
    public TraceabilityPredicate autoAbilities() {
        return autoAbilities(true, true, true, true, true, true, true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GTBTextures.BRIDGMAN_FURNACE_OVERLAY;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.POLARIZER_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityBridgmanFurnace(metaTileEntityId);
    }
}
