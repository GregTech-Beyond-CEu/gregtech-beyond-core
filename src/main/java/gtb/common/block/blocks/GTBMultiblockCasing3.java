package gtb.common.block.blocks;

import gregtech.api.block.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.jetbrains.annotations.NotNull;

public class GTBMultiblockCasing3 extends VariantBlock<GTBMultiblockCasing3.CasingType> {

    public GTBMultiblockCasing3() {
        super(Material.IRON);
        setTranslationKey("gtb_multiblock_casing3");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.ELECTRIC_ARC_FURNACE_CASING));
    }

    @Override
    public boolean canCreatureSpawn(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos,
                                    @NotNull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {


        ELECTRIC_ARC_FURNACE_CASING("electric_arc_furnace_casing"),
        VACUUM_ARC_REMELTING_FURNACE_CASING("vacuum_arc_remelting_furnace_casing"),
        MOLYBDENUM_ELECTRODE_BLOCK("molybdenum_electrode_block"),
        MAGNESIA_BASED_REFACTORY_BRICKS("magnesia_based_refractory_bricks"),
        INDUSTRIAL_EXHAUST_VENT("industrial_exhaust_vent"),
        INCOLOY_600_LINED_STEEL_CASING("incoloy_600_lined_steel_casing"),
        INCOLOY_600_LINED_STEEL_PIPE_CASING("incoloy_600_lined_steel_pipe_casing"),
        ALUMINA_LINED_STEEL_CASING("alumina_lined_steel_casing"),
        FRP_LINED_STEEL_CASING("frp_lined_steel_casing"),
        COAGULATOR_CASING("coagulator_casing"),
        BRIDGMAN_FURNACE_CASING("bridgman_furnace_casing"),
        COLD_BOX("cold_box");
        private final String name;

        CasingType(String name) {
            this.name = name;
        }

        @Override
        public @NotNull String getName() {
            return this.name;
        }
    }
}
