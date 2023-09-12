package Main;

import org.dreambot.api.input.Mouse;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.input.Camera;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;

import org.dreambot.api.methods.walking.path.impl.LocalPath;
import org.dreambot.api.methods.walking.pathfinding.impl.web.WebFinder;


import org.dreambot.api.methods.walking.pathfinding.impl.obstacle.PathObstacle;
import org.dreambot.api.methods.walking.pathfinding.impl.obstacle.impl.ClimbableObstacle;
import org.dreambot.api.methods.walking.pathfinding.impl.obstacle.impl.DestructableObstacle;
import org.dreambot.api.methods.walking.web.node.CustomWebPath;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.methods.walking.pathfinding.impl.local.LocalPathFinder;


import java.util.Objects;
//import main.ListenerChat;


@ScriptManifest(category = Category.MINIGAME, name = "Bas' Motherlode ballsack1", author = "Bredz", version = 2.1D)

public class Main extends AbstractScript {


    private final Area nearHopper = new Area(3746, 5674, 3749, 5673);
    //private ListenerChat chatchecker;
    private final Tile depositHopper = new Tile(3748, 5672, 0);
    private final Area nearCollectSack = new Area(3743, 5656, 3753, 5662);
    private final Tile collectSack = new Tile(3748, 5659, 0);
    private final Tile BankTile = new Tile(3761, 5666, 0);
    private final Tile pathTile = new Tile(3761, 5666, 0);
    private final Area bankArea = new Area(3759, 5670, 3755, 5661);
    private final Area nearBank = new Area(3750, 5677, 3759, 5660);
    private final Area StrutArea = new Area(3741, 5660, 3742, 5671);
    private final Tile strut1 = new Tile(3742, 5669, 0);
    private final Tile strut2 = new Tile(3742, 5663, 0);
    private final Area W_MiningArea = new Area(3724, 5682, 3730, 5658);
    private final Area W_miningExcl = new Area(3724, 5674, 3724, 5676);
    private final Area W_miningExcl1 = new Area(3731, 5660, 3731, 5662);
    private final Area W_miningExcl2 = new Area(3724, 5685, 3722, 5670);
    private final Tile rf1west_miningArea = new Tile(3727, 5683, 0);
    private final Tile rf2west_miningArea = new Tile(3720, 5665, 0);
    private final Area N_MiningArea = new Area(3731, 5686, 3765, 5690);
    private final Tile rf1north_miningArea = new Tile(3762, 5687, 0);
    private final Tile rf2north_miningArea = new Tile(3745, 5689, 0);
    private final Area E_MiningArea = new Area(3764, 5669, 3773, 5660);
    private final Area EastMining = new Area(3764, 5687, 3772, 5642);
    private final Area E_miningExcl = new Area(3724, 5674, 3724, 5676);
    private final Area E_miningExcl1 = new Area(3731, 5660, 3731, 5662);
    private final Area nearLadder = new Area(3755, 5672, 3754, 5671);
    private final Tile miningArea_A_tile = new Tile(3760, 5672, 0);
    private final Area miningArea_A = new Area(3756, 5674, 3762, 5668);
    private final Tile ladderToUpperTile = new Tile(3755, 5673, 0);
    private final Tile ladderToLowerTile = new Tile(3755, 5674, 0);
    private final Area bank_not_above = new Area(3754, 5671, 3759, 5660);
    private final Area upper_ladder_area = new Area(3755, 5675, 3757, 5676);
    private final Tile rf1east_miningArea = new Tile(3766, 5647, 0);
    private final Tile rf2east_miningArea = new Tile(3769, 5658, 0);
    private final Tile rf3east_miningArea = new Tile(3766, 5670, 0);
    private final Tile rf4east_miningArea = new Tile(3768, 5674, 0);
    private final Tile rf5east_miningArea = new Tile(3768, 5679, 0);
    private final Area S_MiningArea = new Area(3763, 5639, 3726, 5644);
    private final Tile rf1South_miningArea = new Tile(3726, 5643, 0);
    private final Tile rf2South_miningArea = new Tile(3756, 5639, 0);
    private final Area Bank_to_NW_Rockfall = new Area(3733, 5679, 3735, 5678);
    private final Tile rf2NW = new Tile(3731, 5683, 0);
    private final Tile rf1NW = new Tile(3733, 5680, 0);
    private final Tile rf3NW = new Tile(3727, 5683, 0);
    private final Area NW_to_Bank = new Area(3726, 5687, 3732, 5682);
    private final Area BetweenRF_NW = new Area(3733, 5681, 3731, 5682);
    //private Tile rf3South_miningArea = new Tile(3745, 5689, 0);
    private final Area inside_West = new Area(3729, 5679, 3727, 5677);
    private final Area Bank_to_SW_Rockfall = new Area(3732, 5652, 3730, 5653);
    private final Area SW_to_bank_Rockfall = new Area(3722, 5654, 3726, 5651);
    private final Area Bank_to_E_Rockfall = new Area(3760, 5652, 3761, 5653);
    private final Area E_to_bank_Rockfall = new Area(3763, 5652, 3765, 5651);
    CustomWebPath WestWalking = new CustomWebPath(
            //West
            new Tile(3727, 5678),
            new Tile(3726, 5668),
            new Tile(3726, 5656),
            new Tile(3726, 5653),
            new Tile(3730, 5652),
            new Tile(3742, 5652),
            new Tile(3749, 5659),
            new Tile(3760, 5666),
            new Tile(3749, 5674),
            new Tile(3738, 5677),


            //East
            new Tile(3757, 5654),
            new Tile(3767, 5652),
            new Tile(3768, 5664),
            new Tile(3767, 5672),
            new Tile(3769, 5679),
            new Tile(3762, 5687),
            new Tile(3750, 5687),
            new Tile(3742, 5689),
            new Tile(3734, 5689),
            new Tile(3730, 5685),

            //upper area
            new Tile(3755, 5676),
            new Tile(3761, 5670),
            new Tile(3730, 5685),
            new Tile(3762, 5662),
            new Tile(3764, 5657),
            new Tile(3750, 5684),
            new Tile(3742, 5685),
            new Tile(3763, 5678),
            new Tile(3759, 5683)
    );
    private String state;
    private boolean toggle1;
    private boolean goToUpper;
    private boolean IsInUpper;
    private boolean moveWest;

    private boolean containsOre() {
        String[] loadOre = {"Coal", "Gold ore", "Adamantite ore", "Runite ore", "Mithril ore", "Copper ore"};
        return Inventory.contains(loadOre);
    }

    private int getRandomPitch() {
        return Calculations.random(Camera.getLowestPitch(), 383);
    }

    public boolean rotateCameraRandomly() {
        return Camera.rotateTo(Calculations.random(0, 2048), getRandomPitch());
    }

    private boolean Sack_full() {
        String counterString = Widgets.getWidgetChild(382, 3, 2).getText();

        int sack_Counter = Integer.parseInt(counterString);
        if (sack_Counter >= 70) {
            this.toggle1 = true;
            return true;
        } else {
            return false;
        }
    }

    private boolean get_pay_dirt_count() {
        int countDirt1 = Inventory.count("Pay-dirt");
        return countDirt1 > 26;
    }

    private void Collect_ore_from_sack() {
        if (!IsInUpper) {
            String counterString = Widgets.getWidgetChild(382, 4, 2).getText();
            int sack_Counter = Integer.parseInt(counterString);
            if (nearCollectSack.contains(Players.getLocal()) && !containsOre()) {
                GameObjects.getTopObjectOnTile(this.collectSack).interact("Search");
                sleepUntil(this::containsOre, Calculations.random(2200, 3500), Calculations.random(2200, 3500));
            }
            if (sack_Counter == 0 && !containsOre()) {
                this.toggle1 = false;
                this.goToUpper = true;
            }
            if (Players.getLocal().distance(this.collectSack) > 5 && !containsOre()) {
                Walking.clickTileOnMinimap(this.collectSack.getRandomizedTile());
                sleepUntil(() -> (Players.getLocal().distance(GameObjects.getTopObjectOnTile(this.collectSack)) < Calculations.random(6, 9)), Calculations.random(7000, 9000),  Calculations.random(7000, 9000));
                GameObjects.getTopObjectOnTile(this.collectSack).interact("Search");
                sleepUntil(this::containsOre, Calculations.random(2200, 3500), Calculations.random(2200, 3500));

            }
            if (Players.getLocal().distance(this.BankTile.getRandomizedTile()) > 5D && containsOre()) {
                Walking.clickTileOnMinimap(this.BankTile.getRandomizedTile());
            } else if (Players.getLocal().distance(this.BankTile) < Calculations.random(4, 9) && containsOre()) {
                GameObjects.getTopObjectOnTile(BankTile).interact("Use");
                sleepUntil(this::containsOre, Calculations.random(200, 700), Calculations.random(200, 700));
                GameObjects.getTopObjectOnTile(BankTile).interact("Use");
                sleepUntil(Bank::isOpen, Calculations.random(5000, 7000), Calculations.random(5000, 7000));
                Bank.depositAllItems();
                sleep(Calculations.random(300, 500));
                Bank.withdraw("Hammer");
                sleepUntil(() -> Inventory.contains("hammer"), Calculations.random(7000, 9000), Calculations.random(7000, 9000));
                Bank.close();

            } else {
                if (sack_Counter == 0 && !containsOre()) {
                    this.toggle1 = false;
                    this.goToUpper = true;
                }
            }
        } else {
            String counterString = Widgets.getWidgetChild(382, 4, 2).getText();
            int sack_Counter = Integer.parseInt(counterString);
            if (sack_Counter > 0) {
                Use_ladder_down();
            }
        }
    }
//
//   private void bankingExample(){
//        case "Running to bank":
//        if (Players.getLocal().distance(this.bankAreaAstral.getCenter()) > 30.0D) {
//            getMagic().castSpell(Lunar.TELE_GROUP_MOONCLAN);
//            sleepUntil(() -> (Players.getLocal().distance(this.bankAreaAstral.getCenter()) < 30.0D), Calculations.random(5000, 6000)); break;
//        }
//        if (Players.getLocal().distance(this.bankAreaAstral.getCenter()) < 30.0D) {
//            if (!getTabs().isOpen(Tab.INVENTORY)) {
//                getTabs().open(Tab.INVENTORY);
//                sleep(Calculations.random(10, 90));
//            }
//            Walking.clickTileOnMinimap(this.bankAreaAstral.getRandomTile());
//            int rand = Calculations.random(0, 10);
//            if (rand <= 6) {
//                sleepUntil(() -> (Players.getLocal().distance(GameObjects.getTopObjectOnTile(this.tile1)) < Calculations.random(6, 9)), Calculations.random(7000, 9000));
//                GameObjects.getTopObjectOnTile(this.tile1).interact("Bank");
//                sleepUntil(() -> Bank.isOpen(), Calculations.random(5000, 7000));
//            }
//            if (rand > 6) {
//                sleepUntil(() -> (Players.getLocal().distance(GameObjects.getTopObjectOnTile(this.tile2)) < Calculations.random(6, 9)), Calculations.random(7000, 9000));
//                GameObjects.getTopObjectOnTile(this.tile2).interact("Bank");
//                sleepUntil(() -> Bank.isOpen(), Calculations.random(5000, 7000));
//    }



    private void dump_Inv_in_hopper() {
        String counterString = Widgets.getWidgetChild(382, 4, 2).getText();
        int sack_Counter = Integer.parseInt(counterString);
        if (Players.getLocal().distance(this.nearHopper.getCenter()) < 11D) {
            while (GameObjects.getTopObjectOnTile(this.strut1).hasAction("Hammer") || GameObjects.getTopObjectOnTile(this.strut2).hasAction("Hammer")) {
                while (GameObjects.getTopObjectOnTile(this.strut2).hasAction("Hammer")) {
                    move_to(strut2.getRandomizedTile());
                    sleepUntil(() -> (Players.getLocal().distance(GameObjects.getTopObjectOnTile(this.strut2)) < Calculations.random(6, 9)), Calculations.random(1400, 2500), Calculations.random(1400, 2500));
                    GameObjects.getTopObjectOnTile(this.strut2).interact("hammer");
                    GameObjects.getTopObjectOnTile(this.strut2).interact("hammer");
                    sleepUntil(() -> (!GameObjects.getTopObjectOnTile(this.strut2).hasAction("Hammer")), Calculations.random(4600, 6300), Calculations.random(4600, 6300));
                }
                while (GameObjects.getTopObjectOnTile(this.strut1).hasAction("Hammer")) {
                    move_to(strut1.getRandomizedTile());
                    sleepUntil(() -> (Players.getLocal().distance(GameObjects.getTopObjectOnTile(this.strut1)) < Calculations.random(6, 9)), Calculations.random(1400, 2500), Calculations.random(1400, 2500));
                    GameObjects.getTopObjectOnTile(this.strut1).interact("hammer");
                    GameObjects.getTopObjectOnTile(this.strut1).interact("hammer");
                    sleepUntil(() -> (!GameObjects.getTopObjectOnTile(this.strut1).hasAction("Hammer")), Calculations.random(4600, 6300), Calculations.random(4600, 6300));
                }
            }
            if (Players.getLocal().distance(this.nearHopper.getCenter()) < 15D) {
                while (Inventory.isFull()) {
                    GameObjects.getTopObjectOnTile(depositHopper).interact("Deposit");
                    sleepUntil(() -> !Inventory.isFull(), Calculations.random(1200, 1700), Calculations.random(1200, 1700));
                    if (sack_Counter == 54) {
                        sleep(5000, 6000);
                    }
                }
                if (!get_pay_dirt_count() && !goToUpper) {
                    this.goToUpper = true;
                }
            } else {
                move_to(nearHopper.getRandomTile());
            }
            if (sack_Counter >= 81) {
                this.toggle1 = true;
            }
        } else {
            move_to(StrutArea.getRandomTile());
        }
    }


    private GameObject getRock() {
        return GameObjects.closest(r -> r != null && this.miningArea_A.contains(r) && r.getName().contains("Ore vein"));
    }

    private boolean WeAreAnimating() {
        for (int i = 0; i < 7; i++) {
            if (Players.getLocal().isAnimating() && !Players.getLocal().isMoving()) {
                return true;
            }
            sleep(250);
        }
        return false;
    }

    private void mine_upper_area() {
        if (getRock().isOnScreen()) {
            log("Isonscreen");
        } else {
            Camera.rotateToEntity(getRock());
            log("rotate to rock");
        }
        if (upper_ladder_area.contains(Players.getLocal())) {
            move_to_upper(miningArea_A.getCenter());
            sleep(400, 700);
            Camera.rotateToEntity(getRock());
            sleep(300, 800);
        } else if (!WeAreAnimating()) {
            if (getRock().isOnScreen()) {
                Camera.rotateToEntity(getRock());
                sleep(100, 277);
                getRock().interact();
            }
        }


        if (Calculations.random(0, 40) < 4) {
            Mouse.moveMouseOutsideScreen();
            log("Anti-ban move of screen");
        }
        if (Mouse.isMouseInScreen()) {
            if (Calculations.random(0, 40) >= 35) {
                Mouse.move();
            }

        }
        Inventory.dropAllExcept("Hammer", "Pay-dirt");
        int rand = Calculations.random(0, 10);
        if (rand == 6) {
            Camera.rotateToEntity(getRock());
            log("rotate to rock");
        }
        if (rand > 30 && rand < 35) {
            //rotateCameraRandomly();
            log("random rotate");
        }
        int countDirt = Inventory.count("Pay-dirt");
        sleepUntil(() -> (Inventory.count("Pay-dirt") > countDirt), Calculations.random(400, 700), Calculations.random(400, 700));
    }


    private void move_to_upper(Tile DestinationTile) {

        LocalPath<Tile> Dest = LocalPathFinder.getLocalPathFinder().calculate(Players.getLocal().getTile(),
                DestinationTile);

        if (GameObjects.closest(r -> r != null && r.getName().equalsIgnoreCase("Rockfall") && r.distance() < 2) != null
                && !Map.canReach(DestinationTile)) {
            if (GameObjects.closest(r -> r != null && r.getName().equalsIgnoreCase("Rockfall"))
                    .interact()) {
                sleep(2300, 2500);
            }
        }
        Walking.walk(Dest.next());
        sleepUntil(() -> Walking.getDestinationDistance() < 4, Calculations.random(2800, 3500), Calculations.random(2800, 3500));
    }

    private void Use_ladder() {
        if (!miningArea_A.contains(Players.getLocal()) || bank_not_above.contains(Players.getLocal())) {
            if (nearLadder.contains(Players.getLocal())) {
                GameObjects.getTopObjectOnTile(ladderToUpperTile).interact("Climb");
                sleepUntil(() -> upper_ladder_area.contains(Players.getLocal()), Calculations.random(2800, 3500), Calculations.random(2800, 3500));
                this.IsInUpper = true;
            } else {
                move_to(nearLadder.getRandomTile());
            }
            if (upper_ladder_area.contains(Players.getLocal())) {
                this.goToUpper = false;
                this.IsInUpper = true;
            }
        } else {
            move_to(nearLadder.getRandomTile());
        }
    }

    private void Use_ladder_down() {
        if (Players.getLocal().distance(upper_ladder_area.getCenter()) < 3D) {
            GameObjects.getTopObjectOnTile(ladderToLowerTile).interact("Climb");
            sleepUntil(() -> nearLadder.contains(Players.getLocal()), Calculations.random(2800, 3500), Calculations.random(2800, 3500));
            this.IsInUpper = false;
        } else if (nearLadder.contains(Players.getLocal())) {
            this.IsInUpper = false;
        } else {
            move_to_upper(upper_ladder_area.getRandomTile());
        }
    }

    private void move_to(Tile DestinationTile) {
        LocalPath<Tile> Dest = LocalPathFinder.getLocalPathFinder().calculate(Players.getLocal().getTile(),
                DestinationTile);
        if (GameObjects.closest(r -> r != null && r.getName().equalsIgnoreCase("Rockfall") && r.distance() < 2) != null
                && !Map.canReach(DestinationTile)) {
            if (GameObjects.closest(r -> r != null && r.getName().equalsIgnoreCase("Rockfall"))
                    .interact()) {
                sleep(2300, 2500);
            }
        }
        Walking.walk(Dest.next());
        sleepUntil(() -> Walking.getDestinationDistance() < 4, Calculations.random(2800, 3500), Calculations.random(2800, 3500));
    }


    public int onLoop() {
        this.state = getState();
        switch (Objects.requireNonNull(this.state)) {
            case "move_to_upper":
                if (Inventory.contains("Hammer")) {
                    move_to(nearLadder.getRandomTile());
                    sleepUntil(() -> nearLadder.contains(Players.getLocal()), Calculations.random(2500, 3500), Calculations.random(2500, 3500));
                    this.goToUpper = true;
                } else if ((!Inventory.contains("Hammer") && Players.getLocal().distance(nearBank.getCenter()) > 10D)) {
                    move_to(nearBank.getRandomTile());
                } else {
                    sleepUntil(() -> (Players.getLocal().distance(GameObjects.getTopObjectOnTile(this.BankTile)) < Calculations.random(6, 9)), Calculations.random(2500, 4000), Calculations.random(2500, 4000));
                    GameObjects.getTopObjectOnTile(this.BankTile).interact("Use");
                    sleepUntil(Bank::isOpen, Calculations.random(5000, 8000), Calculations.random(5000, 8000));
                    while (Bank.isOpen()) {
                        Bank.withdraw("Hammer");
                        sleepUntil(() -> Inventory.contains("hammer"), Calculations.random(1000, 2000), Calculations.random(1000, 2000));
                        Bank.close();
                    }
                }
                break;
            case "running_to_hopper":
                move_to(nearHopper.getRandomTile());
                break;
            case "dump_Inv_in_hopper":
                dump_Inv_in_hopper();
                break;
            case "mine_upper_area":
                mine_upper_area();
                break;
            case "Collect_from_sack":
                Collect_ore_from_sack();
                break;
            case "use_ladder":
                Use_ladder();
                break;
            case "running_down":
                Use_ladder_down();
                break;

        }
        return Calculations.random(400, 700);


    }
    public void add_paths() {
        WebFinder.getWebFinder().addCustomWebPath(WestWalking);

        LocalPathFinder.getLocalPathFinder().addObstacle(new ClimbableObstacle("Ladder", "Climb",
                new Tile(3755, 5672, 0), new Tile(3755, 5675, 0), new Tile(3755, 5673, 0)));

        LocalPathFinder.getLocalPathFinder().addObstacle(new DestructableObstacle("Rockfall", "Mine",
                new Tile(3757, 5676, 0), new Tile(3757, 5677, 0), new Tile(3757, 5678, 0)));
        LocalPathFinder.getLocalPathFinder().addObstacle(new DestructableObstacle("Rockfall", "Mine",
                new Tile(3749, 5684, 0), new Tile(3748, 5684, 0), new Tile(3747, 5685, 0)));
        LocalPathFinder.getLocalPathFinder().addObstacle(new DestructableObstacle("Rockfall", "Mine",
                new Tile(3761, 5668, 0), new Tile(3762, 5668, 0), new Tile(3763, 5668, 0)));

        LocalPathFinder.getLocalPathFinder().addObstacle(new DestructableObstacle("Rockfall", "Mine",
                new Tile(3730, 5683, 0), new Tile(3730, 5683, 0), new Tile(3731, 5683, 0)));
        LocalPathFinder.getLocalPathFinder().addObstacle(new DestructableObstacle("Rockfall", "Mine",
                new Tile(3732, 5680, 0), new Tile(3732, 5680, 0), new Tile(3733, 5680, 0)));
        LocalPathFinder.getLocalPathFinder().addObstacle(new DestructableObstacle("Rockfall", "Mine",
                new Tile(3727, 5682, 0), new Tile(3727, 5682, 0), new Tile(3727, 5683, 0)));
        LocalPathFinder.getLocalPathFinder().addObstacle(new DestructableObstacle("Rockfall", "Mine",
                new Tile(3728, 5652, 0), new Tile(3727, 5652, 0), new Tile(3726, 5652, 0)));
        LocalPathFinder.getLocalPathFinder().addObstacle(new DestructableObstacle("Rockfall", "Mine",
                new Tile(3726, 5653, 0), new Tile(3726, 5654, 0), new Tile(3726, 5655, 0)));

    }

    public void onStart() {
        //this.chatchecker = new ListenerChat();
        this.IsInUpper = false;
        this.goToUpper = true;
        toggle1 = false;
        this.moveWest = true;
        add_paths();
    }

    private String getState() {
        if (this.toggle1 && !IsInUpper) {
            log("Collect_from_sack");
            return "Collect_from_sack";
        } else if (this.toggle1) {
            log("running_down");
            return "running_down";
        }
        if (Sack_full() && !IsInUpper) {
            log("Collect_from_sack");
            return "Collect_from_sack";
        } else if (Sack_full() && IsInUpper) {
            log("running_down");
            return "running_down";
        }
        if (!Inventory.isFull() && !toggle1 && !Sack_full() && goToUpper && !IsInUpper) {
            log("use_ladder");
            return "use_ladder";
        }
        if (!Inventory.isFull() && !miningArea_A.contains(Players.getLocal()) && !toggle1 && !Sack_full() && goToUpper && !IsInUpper) {
            log("use_ladder");
            return "use_ladder";
        } else if (!Inventory.isFull() && miningArea_A.contains(Players.getLocal()) && bank_not_above.contains(Players.getLocal()) && !toggle1 && !Sack_full() && goToUpper && !IsInUpper) {
            log("use_ladder");
            return "use_ladder";
        }
        if (!get_pay_dirt_count() && miningArea_A.contains(Players.getLocal()) && !toggle1) {
            log("mine_upper_area");
            return "mine_upper_area";
        } else if (upper_ladder_area.contains(Players.getLocal())) {
            move_to(miningArea_A.getRandomTile());
        }
        if (get_pay_dirt_count() && Players.getLocal().distance(this.nearHopper.getCenter()) < 5.0D && !toggle1 && !IsInUpper) {
            log("dump in hopper");
            return "dump_Inv_in_hopper";
        }
        if (get_pay_dirt_count() && !toggle1 && IsInUpper) {
            log("running_down");
            return "running_down";
        }
        if (get_pay_dirt_count() && !toggle1 && !IsInUpper) {
            log("running_to_hopper");
            return "running_to_hopper";

        }
        return null;
    }
}
