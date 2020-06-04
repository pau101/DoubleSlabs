package cjminecraft.doubleslabs.addons.thebetweenlands;

import cjminecraft.doubleslabs.api.ISlabSupport;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TheBetweenlandsSlabSupport<T extends Enum<T> & IStringSerializable> implements ISlabSupport {

    private final Class<?> slab;
    private final PropertyEnum<T> slabTypeProperty;
    private final T[] slabTypes;

    public TheBetweenlandsSlabSupport() {
        Class<?> slab;
        PropertyEnum<T> slabTypeProperty;
        T[] slabTypes;
        try {
            slab = Class.forName("thebetweenlands.common.block.structure.BlockSlabBetweenlands");
            slabTypeProperty = (PropertyEnum<T>) slab.getField("HALF").get(null);
            Class<?> slabType = Class.forName("thebetweenlands.common.block.structure.BlockSlabBetweenlands$EnumBlockHalfBL");
            slabTypes = (T[]) slabType.getEnumConstants();
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException ignored) {
            slab = null;
            slabTypeProperty = null;
            slabTypes = null;
        }
        this.slab = slab;
        this.slabTypeProperty = slabTypeProperty;
        this.slabTypes = slabTypes;
    }

    @Override
    public boolean isValid(World world, BlockPos pos, IBlockState state) {
        return slab != null && state.getBlock().getClass().equals(slab) && state.getValue(slabTypeProperty) != slabTypes[2];
    }

    @Override
    public boolean isValid(ItemStack stack, EntityPlayer player, EnumHand hand) {
        return slab != null && stack.getItem() instanceof ItemBlock && ((ItemBlock) stack.getItem()).getBlock().getClass().equals(slab);
    }

    @Override
    public BlockSlab.EnumBlockHalf getHalf(World world, BlockPos pos, IBlockState state) {
        return slab != null && state.getValue(slabTypeProperty) == slabTypes[1] ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP;
    }

    @Override
    public IBlockState getStateForHalf(World world, BlockPos pos, IBlockState state, BlockSlab.EnumBlockHalf half) {
        return slab != null ? state.withProperty(slabTypeProperty, half == BlockSlab.EnumBlockHalf.BOTTOM ? slabTypes[1] : slabTypes[0]) : state;
    }

    @Override
    public boolean areSame(World world, BlockPos pos, IBlockState state, ItemStack stack) {
        return state.getBlock() == ((ItemBlock) stack.getItem()).getBlock();
    }
}