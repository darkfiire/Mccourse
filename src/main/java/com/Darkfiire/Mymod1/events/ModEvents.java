package com.Darkfiire.Mymod1.events;

import com.Darkfiire.Mymod1.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.animation.Event;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;

import java.util.Collection;


public class ModEvents
{

   @SubscribeEvent
public void testEvent(PlayerInteractEvent.LeftClickBlock event)
{
    if(event.getPlayer().getHeldItemMainhand().getItem() == Items.ANCIENT_DEBRIS.getItem())
    {
        PlayerEntity player = event.getPlayer();
        player.getHeldItemMainhand().shrink(1);

        createPortal(event.getWorld(), event.getPos());
    }
}

private void createPortal(World worldIn, BlockPos position)
{
    worldIn.setBlockState(new BlockPos(position.getX() - 1, position.getY() + 1, position.getZ())
            , Blocks.OBSIDIAN.getDefaultState());
    worldIn.setBlockState(new BlockPos(position.getX(), position.getY() + 1, position.getZ())
            , Blocks.OBSIDIAN.getDefaultState());
    worldIn.setBlockState(new BlockPos(position.getX() + 1, position.getY() + 1, position.getZ())
            , Blocks.OBSIDIAN.getDefaultState());
    worldIn.setBlockState(new BlockPos(position.getX() + 2, position.getY() + 1, position.getZ())
            , Blocks.OBSIDIAN.getDefaultState());

    worldIn.setBlockState(new BlockPos(position.getX() - 1, position.getY() + 2, position.getZ())
            , Blocks.OBSIDIAN.getDefaultState());
    worldIn.setBlockState(new BlockPos(position.getX() - 1, position.getY() + 3, position.getZ())
            , Blocks.OBSIDIAN.getDefaultState());
    worldIn.setBlockState(new BlockPos(position.getX() - 1, position.getY() + 4, position.getZ())
            , Blocks.OBSIDIAN.getDefaultState());
    worldIn.setBlockState(new BlockPos(position.getX() - 1, position.getY() + 5, position.getZ())
            , Blocks.OBSIDIAN.getDefaultState());

    worldIn.setBlockState(new BlockPos(position.getX() + 2, position.getY() + 2, position.getZ())
            , Blocks.OBSIDIAN.getDefaultState());
    worldIn.setBlockState(new BlockPos(position.getX() + 2, position.getY() + 3, position.getZ())
            , Blocks.OBSIDIAN.getDefaultState());
    worldIn.setBlockState(new BlockPos(position.getX() + 2, position.getY() + 4, position.getZ())
            , Blocks.OBSIDIAN.getDefaultState());
    worldIn.setBlockState(new BlockPos(position.getX() + 2, position.getY() + 5, position.getZ())
            , Blocks.OBSIDIAN.getDefaultState());

    worldIn.setBlockState(new BlockPos(position.getX() + 1, position.getY() + 5, position.getZ())
            , Blocks.OBSIDIAN.getDefaultState());
    worldIn.setBlockState(new BlockPos(position.getX(), position.getY() + 5, position.getZ())
            , Blocks.OBSIDIAN.getDefaultState());

    worldIn.setBlockState(new BlockPos(position.getX(), position.getY() + 2, position.getZ())
            , Blocks.FIRE.getDefaultState());
}



    @SubscribeEvent
    public void onCopperedSheep(AttackEntityEvent event)
    {
        if(event.getPlayer().getHeldItemMainhand().getItem() == ModItems.COPPERED_APPLE.get())
        {
            if(event.getTarget().isAlive())
            {
                LivingEntity target = (LivingEntity) event.getTarget();
                if(target instanceof SheepEntity);
                {
                    PlayerEntity player = event.getPlayer();

                    player.getHeldItemMainhand().shrink(1);

                    target.addPotionEffect(new EffectInstance(Effects.GLOWING, 600));

                    if(!player.world.isRemote())
                    {
                        String msg = TextFormatting.YELLOW + "Sheep is now glowing!";
                        player.sendMessage(new StringTextComponent(msg), player.getUniqueID());
                    }
                }

            }
        }
    }

    @SubscribeEvent
    public void onEntityHitWithSwordEvent(AttackEntityEvent event)
    {
        if(event.getPlayer().getHeldItemMainhand().getItem() == ModItems.COPPER_SWORD.get())
        {

                LivingEntity target = (LivingEntity) event.getTarget();
                if(target instanceof Entity);
                {
                    target.addPotionEffect(new EffectInstance(Effects.GLOWING, 60 ));
                     target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 10 ));
                }


        }
    }


    @SubscribeEvent
    public void onCopperedSheepDrops(LivingDropsEvent event)
    {
        LivingEntity entity = event.getEntityLiving();

        if(entity instanceof SheepEntity)
        {
            World world = entity.getEntityWorld();
            Collection<ItemEntity> drops = event.getDrops();
              LogManager.getLogger().debug(entity.getActivePotionEffects());

              if(entity.isPotionActive(Effects.GLOWING))
              {
                  drops.add(new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(),
                          new ItemStack(ModItems.COPPER_INGOT.get())));
              }
        }
    }


}


