package dev.nitron.elegance.item;

import com.nitron.nitrogen.util.ColorableItem;
import dev.nitron.elegance.Elegance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class QuoriteItem extends Item implements ColorableItem {

    public QuoriteItem(Settings settings) {
        super(settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        return Elegance.withName(super.getName(stack), 0xff767e);
    }

    @Override
    public int startColor(ItemStack itemStack) {
        return 0xffff767e;
    }

    @Override
    public int endColor(ItemStack itemStack) {
        return 0xff7c052a;
    }

    @Override
    public int backgroundColor(ItemStack itemStack) {
        return 0xf01d0010;
    }
}
