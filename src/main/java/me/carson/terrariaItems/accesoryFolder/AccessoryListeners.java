package me.carson.terrariaItems.accesoryFolder;

import me.carson.terrariaItems.accesoryFolder.accessories.*;
import me.carson.terrariaItems.listenersHandler.ManaManager;
import me.carson.terrariaItems.listenersHandler.PlayerDataHandler;
import me.carson.terrariaItems.listenersHandler.WorldDataHandler;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInputEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class AccessoryListeners implements Listener {

    private final Plugin plugin;
    private NamespacedKey customItemKey;
    private final PlayerDataHandler playerDataInstance=PlayerDataHandler.getInstance();
    private final ManaManager manaManagerInstance=ManaManager.getInstance();
    private final WorldDataHandler worldDataInstance=WorldDataHandler.getInstance();
    private final Set<UUID> usedDoubleJump = new HashSet<>();
    private static final Set<EntityDamageEvent.DamageCause> COUNTERSCARF_CAUSES = Set.of(
            EntityDamageEvent.DamageCause.ENTITY_ATTACK,
            EntityDamageEvent.DamageCause.PROJECTILE,
            EntityDamageEvent.DamageCause.ENTITY_EXPLOSION,
            EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK,
            EntityDamageEvent.DamageCause.LIGHTNING,
            EntityDamageEvent.DamageCause.MAGIC,
            EntityDamageEvent.DamageCause.SONIC_BOOM,
            EntityDamageEvent.DamageCause.DRAGON_BREATH
    );
    private static final Set<EntityDamageEvent.DamageCause> OBSIDIAN_SKULL_DAMAGE = Set.of(
            EntityDamageEvent.DamageCause.FIRE,
            EntityDamageEvent.DamageCause.CAMPFIRE,
            EntityDamageEvent.DamageCause.HOT_FLOOR,
            EntityDamageEvent.DamageCause.FIRE_TICK
    );

    public AccessoryListeners(Plugin plugin){
        this.plugin=plugin;
        customItemKey=new NamespacedKey(plugin, "custom_item_id");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public Boolean hasAccessory(Player player,String id){
        for(ItemStack item :playerDataInstance.getInventory(player.getUniqueId())){
            if(item!=null&&item.hasItemMeta()){
                if(Objects.equals(Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().get(customItemKey, PersistentDataType.STRING), id)){
                    return true;
                }
            }
        }
        return false;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if(hasAccessory(player,"CounterScarf")){
            if(!player.hasCooldown(Material.RED_WOOL)){
                if (COUNTERSCARF_CAUSES.contains(event.getCause())){
                    player.setCooldown(Material.RED_WOOL,600);
                    event.setCancelled(true);
                }
            }
        }
        if(hasAccessory(player,"PanicNecklace")){
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,160,1,false,false,false));
        }
        if(hasAccessory(player,"MagicCuffs")){
            manaManagerInstance.addMana(player.getUniqueId(), event.getDamage()*2);
        }
        if(hasAccessory(player,"HoneyComb")){
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,100,1,false,false,false));
        }
    }

    @EventHandler
    public void onEvokerDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        if (entity.getType() != EntityType.EVOKER){return;}
        if(!worldDataInstance.getHardmode()){return;}
        if(Math.random()<0.15){
            ItemStack custom = SorcererEmblem.getItem(plugin);
            e.getDrops().add(custom);
        }
    }

    @EventHandler
    public void onBlazeDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        if (entity.getType() != EntityType.BLAZE){return;}
        if(!worldDataInstance.getHardmode()){return;}
        if(Math.random()<0.05){
            ItemStack custom = RangerEmblem.getItem(plugin);
            e.getDrops().add(custom);
        }
    }

    @EventHandler
    public void onWitherSkeletonDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        if (entity.getType() != EntityType.WITHER_SKELETON){return;}
        if(!worldDataInstance.getHardmode()){return;}
        if(Math.random()<0.05){
            ItemStack custom = WarriorEmblem.getItem(plugin);
            e.getDrops().add(custom);
        }
    }

    @EventHandler
    public void onKnockback(EntityKnockbackEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if(hasAccessory(player,"CobaltShield")){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if(((event.getCause() == EntityDamageEvent.DamageCause.FALL)||(event.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL))){
            if(hasAccessory(player,"LuckyHorseshoe")){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onFireDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if(hasAccessory(player,"ObsidianSkull")){
            if (OBSIDIAN_SKULL_DAMAGE.contains(event.getCause())){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(((Entity)player).isOnGround()){
            usedDoubleJump.remove(player.getUniqueId());
        }
    }

    @EventHandler
    public void onCloudBottleDoubleJump(PlayerInputEvent event){
        if(!event.getInput().isJump()){return;}
        Player player = event.getPlayer();
        if(!hasAccessory(player,"CloudInABottle")){return;}
        if(((Entity)player).isOnGround()){
            return;
        }
        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR){return;}
        if(usedDoubleJump.contains(player.getUniqueId())){return;}
        usedDoubleJump.add(player.getUniqueId());
        player.setVelocity(player.getVelocity().setY(0.5));
        player.getWorld().playSound(player.getLocation(), "terraria:double_jump", 1.0F, 1.0F);
        player.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, player.getLocation(), 20, 0.2, 0.2, 0.2, 0.05);
    }

    @EventHandler
    public void onTsunamiDoubleJump(PlayerInputEvent event){
        if(!event.getInput().isJump()){return;}
        Player player = event.getPlayer();
        if(!hasAccessory(player,"TsunamiInABottle")){return;}
        if(((Entity)player).isOnGround()){
            return;
        }
        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR){return;}
        if(usedDoubleJump.contains(player.getUniqueId())){return;}
        usedDoubleJump.add(player.getUniqueId());
        player.setVelocity(player.getVelocity().setY(0.6));
        player.getWorld().playSound(player.getLocation(), "terraria:double_jump", 1.0F, 1.0F);
        player.getWorld().spawnParticle(Particle.BUBBLE, player.getLocation(), 20, 0.2, 0.2, 0.2, 0.05);
    }

    @EventHandler
    public void onBlizzardDoubleJump(PlayerInputEvent event){
        if(!event.getInput().isJump()){return;}
        Player player = event.getPlayer();
        if(!hasAccessory(player,"BlizzardInABottle")){return;}
        if(((Entity)player).isOnGround()){
            return;
        }
        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR){return;}
        if(usedDoubleJump.contains(player.getUniqueId())){return;}
        usedDoubleJump.add(player.getUniqueId());
        player.getWorld().playSound(player.getLocation(), "terraria:double_jump", 1.0F, 1.0F);
        final int[] timeLeft = {10};
        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            player.setVelocity(player.getVelocity().setY(0.4));
            player.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, player.getLocation(), 20, 0.2, 0.2, 0.2, 0.05);
            timeLeft[0]--;
            if (timeLeft[0] <= 0) {
                task.cancel();
            }
        }, 0L, 1L);
    }

    @EventHandler
    public void onSandstormDoubleJump(PlayerInputEvent event){
        if(!event.getInput().isJump()){return;}
        Player player = event.getPlayer();
        if(!hasAccessory(player,"SandstormInABottle")){return;}
        if(((Entity)player).isOnGround()){
            return;
        }
        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR){return;}
        if(usedDoubleJump.contains(player.getUniqueId())){return;}

        player.getWorld().playSound(player.getLocation(), "terraria:double_jump", 0.5F, 1.0F);
        usedDoubleJump.add(player.getUniqueId());
        final int[] timeLeft = {15};
        Bukkit.getScheduler().runTaskTimer(plugin, task -> {
            player.setVelocity(player.getVelocity().setY(0.4));
            player.getWorld().spawnParticle(Particle.DUST_PLUME, player.getLocation(), 20, 0.2, 0.2, 0.2, 0.05);
            timeLeft[0]--;
            if (timeLeft[0] <= 0) {
                task.cancel();
            }
        }, 0L, 1L);
    }

    @EventHandler
    private void onPoison(EntityPotionEffectEvent event){
        if (!(event.getEntity() instanceof Player player)) return;
        PotionEffect newEffect = event.getNewEffect();
        if (newEffect == null) return;
        if (newEffect.getType() == PotionEffectType.POISON) {
            if(hasAccessory(player,"Bezoar")){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onBlind(EntityPotionEffectEvent event){
        if (!(event.getEntity() instanceof Player player)) return;

        PotionEffect newEffect = event.getNewEffect();
        if (newEffect == null) return;

        if (newEffect.getType() == PotionEffectType.DARKNESS || newEffect.getType() == PotionEffectType.BLINDNESS) {
            if(hasAccessory(player,"Blindfold")){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onSlow(EntityPotionEffectEvent event){
        if (!(event.getEntity() instanceof Player player)) return;

        PotionEffect newEffect = event.getNewEffect();
        if (newEffect == null) return;

        if (newEffect.getType() == PotionEffectType.SLOWNESS) {
            if (hasAccessory(player,"FastClock")){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onWeakness(EntityPotionEffectEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        PotionEffect newEffect = event.getNewEffect();
        if (newEffect == null) return;

        if (newEffect.getType() == PotionEffectType.WEAKNESS) {
            if (hasAccessory(player,"Vitamins")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        if (entity.getType() != EntityType.ZOMBIE){return;}
        if(Math.random()<0.02){
            e.getDrops().add(Shackle.getItem(plugin));
        }
    }

    @EventHandler
    public void onBrush(BlockDropItemEvent event){
        Block block = event.getBlock();
        if(!(block.getType()==Material.SUSPICIOUS_SAND||block.getType()==Material.SUSPICIOUS_GRAVEL)){return;}
        if(Math.random()<0.25){
            ItemStack item = AncientFossil.getItem(plugin);
            block.getWorld().dropItem(block.getLocation().add(0,1,0), item);
        }
    }
}
