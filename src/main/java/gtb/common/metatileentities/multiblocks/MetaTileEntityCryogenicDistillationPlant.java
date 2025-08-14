package gtb.common.metatileentities.multiblocks;

import gregicality.multiblocks.api.unification.GCYMMaterials;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.util.RelativeDirection;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gtb.api.recipes.GTBRecipeMaps;
import gtb.common.block.GTBMetaBlocks;
import gtb.common.block.blocks.GTBMultiblockCasing2;
import gtb.common.block.blocks.GTBMultiblockCasing3;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntityCryogenicDistillationPlant extends RecipeMapMultiblockController {

    public MetaTileEntityCryogenicDistillationPlant(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTBRecipeMaps.CRYOGENIC_DISTILLATION_PLANT_RECIPES);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.DOWN, RelativeDirection.BACK)
                .aisle("CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC")
                .aisle("CCC", "CXC", "CXC", "CXC", "CXC", "CXC", "CXC", "CXC", "CXC", "CXC", "CXC", "CXC", "CXC", "CCC")
                .aisle("CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CSC")
                .where('S', selfPredicate())
                .where('C',
                        states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN))
                                .setMinGlobalLimited(25)
                                .or(autoAbilities()))
                .where('X', states(GTBMetaBlocks.GTB_MULTIBLOCK_CASING3.getState(GTBMultiblockCasing3.CasingType.COLD_BOX)))
                .where('~', any())
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
        return new MetaTileEntityCryogenicDistillationPlant(metaTileEntityId);
    }
}
