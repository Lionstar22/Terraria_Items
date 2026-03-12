package me.carson.terrariaItems.listenersHandler;

import me.carson.terrariaItems.accesoryFolder.Accessory;
import me.carson.terrariaItems.accesoryFolder.AccessoryManager;
import me.carson.terrariaItems.armourFolder.ArmorManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class PlayerDataHandler {

    private static PlayerDataHandler instance;
    private final AccessoryManager accessoryInstance = AccessoryManager.getInstance();
    private final ArmorManager armorInstance = ArmorManager.getInstance();
    private final File file;
    private final YamlConfiguration config;

    public PlayerDataHandler(Plugin plugin){
        file = new File(plugin.getDataFolder(), "playerData.yml");
        if (!file.exists()) {
            try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public Boolean getShowMsg(UUID id){
        return config.getBoolean(id +".show_message",true);
    }
    public void setShowMsg(UUID id,Boolean set){
        config.set(id +".show_message",set);
    }
    public void toggleMsg(UUID id){
        setShowMsg(id,!getShowMsg(id));
    }

    public double getMaxMana(UUID id){
        return config.getDouble(id +".max_mana",20);
    }
    public void setMaxMana(UUID id, double x){
        config.set(id +".max_mana",x);
    }

    
    public List<ItemStack> getInventory(UUID id){
        return (List<ItemStack>) config.getList(id+".accessory_inv");
    }
    public void setInventory(UUID id,List<ItemStack> list){
        config.set(id+".accessory_inv",list);
    }

    public List<ItemStack> getVanity(UUID id){
        return (List<ItemStack>) config.getList(id+".vanity_inv");
    }
    public void setVanity(UUID id,List<ItemStack> list){
        config.set(id+".vanity_inv",list);
    }

    public double getBonusDamage(UUID id){return config.getDouble(id +".bonus_damage",0);}
    public void setBonusDamage(UUID id, double bonus){config.set(id +".bonus_damage",bonus);}
    public void addBonusDamage(UUID id,double add){setBonusDamage(id,getBonusDamage(id)+add);}
    public void subtractBonusDamage(UUID id,double minus){
        if((getBonusDamage(id)-minus)<0){
            setBonusDamage(id,0);
        }else{
            setBonusDamage(id,getBonusDamage(id)-minus);
        }

    }

    public double getBonusMelee(UUID id){return config.getDouble(id +".bonus_melee",0);}
    public void setBonusMelee(UUID id, double bonus){config.set(id +".bonus_melee",bonus);}
    public void addBonusMelee(UUID id,double add){setBonusMelee(id,getBonusMelee(id)+add);}
    public void subtractBonusMelee(UUID id,double minus){
        if((getBonusMelee(id)-minus)<0){
            setBonusMelee(id,0);
        }else {
            setBonusMelee(id,getBonusMelee(id)-minus);
        }

    }

    public double getBonusRanged(UUID id){return config.getDouble(id +".bonus_ranged",0);}
    public void setBonusRanged(UUID id, double bonus){config.set(id +".bonus_ranged",bonus);}
    public void addBonusRanged(UUID id,double add){setBonusRanged(id,getBonusRanged(id)+add);}
    public void subtractBonusRanged(UUID id,double minus){
        if((getBonusRanged(id)-minus)<0){
            setBonusRanged(id,0);
        }else {
            setBonusRanged(id,getBonusRanged(id)-minus);
        }
    }

    public double getBonusMagic(UUID id){return config.getDouble(id +".bonus_magic",0);}
    public void setBonusMagic(UUID id, double bonus){config.set(id +".bonus_magic",bonus);}
    public void addBonusMagic(UUID id,double add){setBonusMagic(id,getBonusMagic(id)+add);}
    public void subtractBonusMagic(UUID id,double minus){
        if((getBonusMagic(id)-minus)<0){
            setBonusMagic(id,0);
        }else{
            setBonusMagic(id,getBonusMagic(id)-minus);
        }
    }

    public double getDamageReduction(UUID id){return config.getDouble(id +".damage_reduction",0);}
    public void setDamageReduction(UUID id, double bonus){config.set(id +".damage_reduction",bonus);}
    public void addDamageReduction(UUID id,double add){setDamageReduction(id,getDamageReduction(id)+add);}
    public void subtractDamageReduction(UUID id,double minus){
        if((getDamageReduction(id)-minus)<0){
            setDamageReduction(id,0);
        }else{
            setDamageReduction(id,getDamageReduction(id)-minus);
        }
    }


    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetBonuses(Player player){
        setBonusMelee(player.getUniqueId(),0.0);
        setBonusRanged(player.getUniqueId(),0.0);
        setBonusMagic(player.getUniqueId(),0.0);
        setBonusDamage(player.getUniqueId(),0.0);
        setDamageReduction(player.getUniqueId(),0.0);
        player.setWalkSpeed(0.2f);

        for(ItemStack item:getInventory(player.getUniqueId())){
            Accessory accessory=accessoryInstance.getAccessory(item);
            if(accessory!=null){
                accessory.activateEffect(player);
            }
        }
        for (ItemStack armor : player.getInventory().getArmorContents()) {
            if(armorInstance.getArmorPiece(armor)!=null){armorInstance.getArmorPiece(armor).activateArmorEffect(player);}
        }
    }

    public static void initialize(JavaPlugin plugin) {
        instance = new PlayerDataHandler(plugin);
    }

    public static PlayerDataHandler getInstance() {
        return instance;
    }

}
