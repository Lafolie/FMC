package lafolie.fmc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lafolie.fmc.element.ElementalAspect;
import lafolie.fmc.element.ElementalObject;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class FMCClient implements ClientModInitializer
{
	public static final Logger LOG = LoggerFactory.getLogger("final-minecraft-client");

	@Override
	public void onInitializeClient()
	{
		// TODO Auto-generated method stub
		// throw new UnsupportedOperationException("Unimplemented method 'onInitializeClient'");

		ItemTooltipCallback.EVENT.register(FMCClient::getTooltip);
	}
	
	private static void getTooltip(ItemStack stack, TooltipContext context, List<Text> lines)
	{
		ElementalObject obj = (ElementalObject)(Object)stack;

		for(ElementalAspect element : ElementalAspect.values())
		{
			int total = obj.getWeakResistAffinity(element);
			// LOG.info("{} weak resist: {}", stack.toString(), total);
			if(total > 0)
			{
				lines.add(Text.translatable("fmc.element.tooltip.elemental" , Text.translatable(element.getLangKey())));
				lines.add(Text.literal(String.format("Total: %s", total)));
			}
		}
	}
}
