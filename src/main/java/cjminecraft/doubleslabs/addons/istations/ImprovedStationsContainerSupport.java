package cjminecraft.doubleslabs.addons.istations;

import cjminecraft.doubleslabs.api.IContainerSupport;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.stats.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.registries.ObjectHolder;

public class ImprovedStationsContainerSupport implements IContainerSupport {

    @ObjectHolder("improved-stations:crafting_station_slab")
    public static final Block CRAFTING_STATION_SLAB = null;

    @Override
    public boolean isValid(World world, BlockPos pos, BlockState state) {
        return state.getBlock() == CRAFTING_STATION_SLAB;
    }

    @Override
    public INamedContainerProvider getNamedContainerProvider(World world, BlockPos pos, BlockState state, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        return (INamedContainerProvider) world.getTileEntity(pos);
    }

    @Override
    public void onClicked(World world, BlockPos pos, BlockState state, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        player.addStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
    }
}
