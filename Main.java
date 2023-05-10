import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.*;

public class Main extends Application{
    public double razmer;
    public Game game;
    public void start(Stage primaryStage) throws IOException{
        File file = new File("map.txt.txt");
        Scanner scan = new Scanner(file);
        Player player = new MyPlayer();
        game = null;
        try{
            game = new Game(new Map(scan));
        }
        catch(InvalidMapException e){ // custom exception
            System.out.println(e.getMessage());
            System.exit(0);
        }
        game.addPlayer(player);
        StackPane fon = new StackPane(new Rectangle(1000, 900, Color.BLACK));
        Pane mapin = game.getMap().pane();
        Position pos = player.getPosition();
        Tank obj = new Tank(game.getMap().getSize());
        Pane tank = obj.getPane();
        mapin.getChildren().add(tank);
        Rectangle rec1 = new Rectangle(0,0,150,900);
        rec1.setFill(Color.GRAY);
        Rectangle rec2 = new Rectangle(150, 0, 850, 100);
        rec2.setFill(Color.GRAY);
        Rectangle rec3 = new Rectangle(150, 800, 850, 100);
        rec3.setFill(Color.GRAY);
        Rectangle rec4 = new Rectangle(850, 100, 150, 700);
        rec4.setFill(Color.GRAY);
        mapin.getChildren().addAll(rec1, rec2, rec3, rec4);
        razmer = game.getMap().getSize();
        tank.setLayoutX(150 + pos.getX()*(700/razmer));
        tank.setLayoutY(100 + pos.getY()*(700/razmer));
        Bullet bull = new Bullet(game);
        mapin.getChildren().add(bull);
        fon.getChildren().add(mapin);
        Scene scene = new Scene(fon, 1000, 900);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case UP:
                    int number = 0;
                    int ber =0;
                    Node po = null;
                    tank.setRotate(0);
                    for(int i=0; i<mapin.getChildren().size(); i++) {
                        if(!game.getMap().forTree.containsValue(mapin.getChildren().get(i))) {
                            if (mapin.getChildren().get(i).contains(tank.getLayoutX()+0.2, tank.getLayoutY() - 0.2)) {
                                number++;
                            } else if (mapin.getChildren().get(i).contains(tank.getLayoutX() + 700 / razmer - 0.2, tank.getLayoutY() - 0.2)) {
                                number++;
                            }
                        }else if(game.getMap().forTree.containsValue(mapin.getChildren().get(i))){
                            if (mapin.getChildren().get(i).contains(tank.getLayoutX()+0.2, tank.getLayoutY() -0.2)) {
                                ber++;
                                po = mapin.getChildren().get(i);
                            }else if(mapin.getChildren().get(i).contains(tank.getLayoutX() + 700 / razmer - 0.2, tank.getLayoutY() - 0.2)){
                                ber++;
                                po = mapin.getChildren().get(i);
                            }
                        }
                    }
                    if(number==0 && ber!=0){
                            tank.setLayoutY(tank.getLayoutY() - (10*(5/razmer)));
                            mapin.getChildren().remove(po);
                            mapin.getChildren().add(po);
                    }else if(number==0){
                        tank.setLayoutY(tank.getLayoutY()-(10*(5/razmer)));
                    }
                    break;
                case DOWN:
                    int chislo = 0;
                    int oooo = 0;
                    Node od = null;
                    tank.setRotate(180);
                    for(int i=0; i<mapin.getChildren().size(); i++){
                        if(!game.getMap().forTree.containsValue(mapin.getChildren().get(i))) {
                            if (mapin.getChildren().get(i).contains(tank.getLayoutX()+0.2, tank.getLayoutY() + 700 / razmer +0.2)) {
                                chislo++;
                            } else if (mapin.getChildren().get(i).contains(tank.getLayoutX() + 700 / razmer - 0.2, tank.getLayoutY() + 700 / razmer + 0.2)) {
                                chislo++;
                            }
                        }else if(game.getMap().forTree.containsValue(mapin.getChildren().get(i))){
                            if (mapin.getChildren().get(i).contains(tank.getLayoutX()+0.2, tank.getLayoutY() + 700 / razmer + 0.2)) {
                                oooo++;
                                od = mapin.getChildren().get(i);
                            }else if(mapin.getChildren().get(i).contains(tank.getLayoutX() + 700 / razmer - 0.2, tank.getLayoutY() + 700 / razmer + 0.2)){
                                oooo++;
                                od = mapin.getChildren().get(i);
                            }
                        }
                    }
                    if(chislo==0 && oooo!=0){
                        tank.setLayoutY(tank.getLayoutY()+(10*(5/razmer)));
                        mapin.getChildren().remove(od);
                        mapin.getChildren().add(od);
                    }else if(chislo==0){
                        tank.setLayoutY(tank.getLayoutY()+(10*(5/razmer)));
                    }
                break;
                case RIGHT:
                    int san = 0;
                    int nnn = 0;
                    Node ed = null;
                    tank.setRotate(90);
                    for(int i=0; i<mapin.getChildren().size(); i++){
                        if(!game.getMap().forTree.containsValue(mapin.getChildren().get(i))) {
                            if (mapin.getChildren().get(i).contains(tank.getLayoutX() + 700 / razmer + 0.2, tank.getLayoutY()+0.2)) {
                                san++;
                            } else if (mapin.getChildren().get(i).contains(tank.getLayoutX() + 700 / razmer + 0.2, tank.getLayoutY() + 700 / razmer - 0.2)) {
                                san++;
                            }
                        }else if(game.getMap().forTree.containsValue(mapin.getChildren().get(i))) {
                            if (mapin.getChildren().get(i).contains(tank.getLayoutX() + 700 / razmer + 0.2, tank.getLayoutY()+0.2)){
                                nnn++;
                                ed = mapin.getChildren().get(i);
                            }else if(mapin.getChildren().get(i).contains(tank.getLayoutX() + 700 / razmer + 0.2, tank.getLayoutY() + 700 / razmer - 0.2)) {
                                nnn++;
                                ed = mapin.getChildren().get(i);
                            }
                        }
                    }
                    if(san==0 && nnn!=0) {
                        tank.setLayoutX(tank.getLayoutX() + (10*(5/razmer)));
                        mapin.getChildren().remove(ed);
                        mapin.getChildren().add(ed);
                    }else if(san == 0){
                        tank.setLayoutX(tank.getLayoutX() + (10*(5/razmer)));
                    }
                break;
                case LEFT:
                    int soz = 0;
                    int yep = 0;
                    Node qwe = null;
                    tank.setRotate(270);
                    for(int i=0; i<mapin.getChildren().size(); i++){
                        if(!game.getMap().forTree.containsValue(mapin.getChildren().get(i))) {
                            if (mapin.getChildren().get(i).contains(tank.getLayoutX() -0.2, tank.getLayoutY()+0.2)) {
                                soz++;
                            } else if (mapin.getChildren().get(i).contains(tank.getLayoutX() - 0.2, tank.getLayoutY() + 700 / razmer - 0.2)) {
                                soz++;
                            }
                        }else if(game.getMap().forTree.containsValue(mapin.getChildren().get(i))) {
                            if (mapin.getChildren().get(i).contains(tank.getLayoutX() - 0.2, tank.getLayoutY() + 0.2)){
                                yep++;
                                qwe = mapin.getChildren().get(i);
                            }
                            else if (mapin.getChildren().get(i).contains(tank.getLayoutX() - 0.2, tank.getLayoutY() + 700 / razmer - 0.2)){
                                yep++;
                                qwe = mapin.getChildren().get(i);
                            }
                        }
                    }
                    if(soz==0 && yep != 0) {
                        tank.setLayoutX(tank.getLayoutX() -(10*(5/razmer)));
                        mapin.getChildren().remove(qwe);
                        mapin.getChildren().add(qwe);
                    }else if(soz==0){
                        tank.setLayoutX(tank.getLayoutX()-(10*(5/razmer)));
                    }
                    break;
                default: break;
            }
        });
        scene.setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.SPACE) {
                switch ((int) tank.getRotate()) {
                    case 0:
                        bull.setcrs(tank.getLayoutX() + 350 / razmer, tank.getLayoutY() - 1, tank.getRotate(), mapin);
                        break;
                    case 90:
                        bull.setcrs(tank.getLayoutX() + 700 / razmer + 1, tank.getLayoutY() + 350 / razmer, tank.getRotate(), mapin);
                        break;
                    case 180:
                        bull.setcrs(tank.getLayoutX() + 350 / razmer, tank.getLayoutY() + 700 / razmer + 1, tank.getRotate(), mapin);
                        break;
                    case 270:
                        bull.setcrs(tank.getLayoutX() - 1, tank.getLayoutY() + 350 / razmer, tank.getRotate(), mapin);
                        break;
                    default:
                        break;
                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

class Map{
    private int razme;
    private double razmer;
    private char[][] chenada;
    private Image water = new Image("file:water.png");
    private Image tree = new Image("file:tree.png");
    private Image brickWall = new Image("file:brickWall.png");
    private Image steelWall = new Image("file:steelwall.png");
    HashMap<String, ImageView> forWater = new HashMap<>();
    HashMap<String, ImageView> forTree = new HashMap<>();
    HashMap<String, ImageView> forBrick = new HashMap<>();
    HashMap<String, ImageView> forSteel = new HashMap<>();

    Map(Scanner netprinter) throws InvalidMapException{
        try{
            razme = netprinter.nextInt();
            if(razme==0){
                throw new InvalidMapException("Map size can not be zero");
            }
            chenada = new char[razme][razme];
            for(int p=0; p<razme; p++){
                for(int l=0; l<razme; l++){
                    chenada[p][l] = netprinter.next().charAt(0);
                }
            }
            razmer = razme;
        }catch(Exception emesowibka){
            throw new InvalidMapException();
        }
    }
    int getSize(){
        return razme;
    }
    char getValueAt(int q, int p){
        return chenada[q][p];
    }

    Pane pane(){
        Pane temp = new Pane();
        for(int i=0; i<razmer; i++){
            for(int j=0; j<razmer; j++){
                switch (chenada[i][j]){
                    case 'S': forSteel.put(150+ j*(700/razmer) +" "+100+ i*(700/razmer), new ImageView(steelWall));
                    forSteel.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)).setFitWidth(700/razmer);
                    forSteel.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)).setFitHeight(700/razmer);
                    forSteel.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)).setX(150+ j*(700/razmer));
                    forSteel.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)).setY(100+ i*(700/razmer));
                    temp.getChildren().add(forSteel.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)));
                    break;
                    case 'B': forBrick.put(150+ j*(700/razmer) +" "+100+ i*(700/razmer), new ImageView(brickWall));
                        forBrick.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)).setFitWidth(700/razmer);
                        forBrick.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)).setFitHeight(700/razmer);
                        forBrick.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)).setX(150+ j*(700/razmer));
                        forBrick.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)).setY(100+ i*(700/razmer));
                        temp.getChildren().add(forBrick.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)));
                        break;
                    case 'W': forWater.put(150+ j*(700/razmer) +" "+100+ i*(700/razmer), new ImageView(water));
                        forWater.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)).setFitWidth(700/razmer);
                        forWater.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)).setFitHeight(700/razmer);
                        forWater.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)).setX(150+ j*(700/razmer));
                        forWater.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)).setY(100+ i*(700/razmer));
                        temp.getChildren().add(forWater.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)));
                        break;
                    case 'T': forTree.put(150+ j*(700/razmer) +" "+100+ i*(700/razmer), new ImageView(tree));
                        forTree.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)).setFitWidth(700/razmer);
                        forTree.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)).setFitHeight(700/razmer);
                        forTree.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)).setX(150+ j*(700/razmer));
                        forTree.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)).setY(100+ i*(700/razmer));
                        temp.getChildren().add(forTree.get(150+ j*(700/razmer) +" "+100+ i*(700/razmer)));
                        break;
                    default: break;
                }
            }
        }
        return temp;
    }
}

class InvalidMapException extends Exception{
    public InvalidMapException(){
    }

    public InvalidMapException(String soylem){
        System.out.println(soylem);
        System.exit(0);
    }
    public String getMessage(){
        return "Not enough map elements";
    }
}

class Game{
    private Map netgps;
    Game(Map dagps){
        netgps = dagps;
    }
    void setMap(Map kartaemes){
        netgps = kartaemes;
    }
    void addPlayer(Player netouin){
        netouin.setMap(netgps);
    }

    Map getMap(){
        return netgps;
    }
}

interface Player{
    void moveRight();
    void moveLeft();
    void moveUp();
    void moveDown();
    void setMap(Map disngoy);
    Position getPosition();
}

class MyPlayer implements Player{
    private Map dada;
    private Position agaga;
    MyPlayer(){
    }
    @Override
    public void moveRight() {
        try{
            if(agaga.getY()!=dada.getSize()){
                if(dada.getValueAt(agaga.getX(), agaga.getY()+1)!= '1'){
                    agaga.setY(agaga.getY()+1);
                }
            }
        }catch(Exception emesowibka){
        }
    }
    public void moveLeft() {
        try{
            if(agaga.getY()!=0){
                if(dada.getValueAt(agaga.getX(), agaga.getY()-1) != '1'){
                    agaga.setY(agaga.getY()-1);
                }
            }
        }catch(Exception emesowibka){
        }
    }
    public void moveUp() {
        try{
            if(agaga.getX()!=0){
                if(dada.getValueAt(agaga.getX()-1, agaga.getY()) != '1'){
                    agaga.setX(agaga.getX()-1);
                }
            }
        }catch(Exception emesowibka){
        }
    }
    public void moveDown() {
        try{
            if(agaga.getX()!= dada.getSize()){
                if(dada.getValueAt(agaga.getX()+1, agaga.getY())!= '1'){
                    agaga.setX(agaga.getX()+1);
                }
            }
        }catch(Exception emesowibka){
        }
    }
    public void setMap(Map lol){
        dada = lol;
        for(int g=0; g< dada.getSize(); g++){
            for(int f=0; f<dada.getSize(); f++){
                if(dada.getValueAt(f,g)=='P')
                    agaga = new Position(g,f);
            }
        }
    }
    public Position getPosition(){
        return agaga;
    }
}

class Position{
    private int h;
    private int j;
    Position(int t, int r){
        h = t;
        j = r;
    }
    void setX(int q){
        h = q;
    }
    void setY(int w){
        j = w;
    }
    int getX(){
        return h;
    }
    int getY(){
        return j;
    }
    boolean equals(Position hihihi){
        if(hihihi.getX()==h && hihihi.getY()==j)
            return true;
        else
            return false;
    }
    public String toString(){
        return "("+h+","+j+")";
    }
}

class Tank extends MyPlayer{
    private Pane tankPane = new Pane();
    private Position position;
    private int taks;

    Tank(int ezis){
        position = getPosition();
        taks = ezis;
    }

    Pane getPane(){
        Rectangle reccc = new Rectangle(0, 0, 140/taks, 700/taks);
        reccc.setFill(Color.GRAY);
        Rectangle re = new Rectangle(560/taks, 0, 140/taks, 700/taks);
        re.setFill(Color.GRAY);
        Circle cir = new Circle(350/taks, 350/taks, 140/taks);
        cir.setFill(Color.DARKGREEN);
        Rectangle rec = new Rectangle(140/taks, 0, 420/taks, 700/taks);
        rec.setFill(Color.GREEN);
        Rectangle la = new Rectangle(350/taks-35/taks, 0, 70/taks, 350/ taks);
        la.setFill(Color.DARKGREEN);
        la.setStroke(Color.GRAY);
        tankPane.getChildren().addAll(reccc, re, rec, la, cir);
        return tankPane;
    }
}

class Bullet extends Pane{
    private Circle pulya;
    private double x;
    private double y;
    private int rotate;
    private Timeline anim;
    private HashMap<Node, Integer> ran;
    private Game game;
    private ArrayList<String> arr;
    Bullet(Game game){
        this.game = game;
        arr = new ArrayList<>();
        arr.addAll(game.getMap().forBrick.keySet());
        ran = new HashMap<>();
        for(int i=0; i< arr.size(); i++){
            ran.put(game.getMap().forBrick.get(arr.get(i)) , 0);
        }
    }

    void setcrs(double l, double d, double gra, Pane pa){
        x= l;
        y = d;
        rotate = (int)gra;
        pulya = new Circle(l, d,5);
        pulya.setFill(Color.GRAY);
        getChildren().add(pulya);
        anim = new Timeline(new KeyFrame(Duration.millis(50), event -> atack(pa)));
        anim.setCycleCount(Timeline.INDEFINITE);
        anim.setRate(20);
        anim.play();
    }



    void stop(){
        anim.stop();
    }

    protected void atack(Pane ap){
        String ee = getClass()+"";
        switch (rotate) {
                case 0:
                    int soz = 0;
                    for(int i=0; i<ap.getChildren().size(); i++){
                        if(!game.getMap().forTree.containsValue(ap.getChildren().get(i))) {
                            if (!game.getMap().forWater.containsValue(ap.getChildren().get(i))) {
                                String ss = ""+ap.getChildren().get(i).getClass();
                                if(!ss.equals(ee)) {
                                    if (ap.getChildren().get(i).contains(x, y-1)) {
                                        soz++;
                                        if(game.getMap().forBrick.containsValue(ap.getChildren().get(i))){
                                            int re = ran.get(ap.getChildren().get(i));
                                            re++;
                                            Node ed = ap.getChildren().get(i);
                                            if(re==4){
                                                ap.getChildren().remove(ed);
                                                ran.remove(ed);
                                            }else{
                                                ran.remove(ed);
                                                ran.put(ed, re);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(soz==0){
                         y = y-2;
                         pulya.setCenterY(y);
                    }else{
                        stop();
                        getChildren().remove(pulya);
                    }
                    break;
                case 90:
                    int so = 0;
                    for(int i=0; i<ap.getChildren().size(); i++){
                        if(!game.getMap().forTree.containsValue(ap.getChildren().get(i))) {
                            if (!game.getMap().forWater.containsValue(ap.getChildren().get(i))) {
                                String ss = ap.getChildren().get(i).getClass()+"";
                                if(!ss.equals(ee)) {
                                    if (ap.getChildren().get(i).contains(x+1, y)) {
                                        so++;
                                        if(game.getMap().forBrick.containsValue(ap.getChildren().get(i))){
                                            int re = ran.get(ap.getChildren().get(i));
                                            re++;
                                            Node on = ap.getChildren().get(i);
                                            if(re==4){
                                                ap.getChildren().remove(on);
                                                ran.remove(on);
                                            }else{
                                                ran.remove(on);
                                                ran.put(on , re);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(so==0){
                        x = x+2;
                        pulya.setCenterX(x);
                    }else{
                        stop();
                        getChildren().remove(pulya);
                    }
                    break;
                case 180:
                    int os = 0;
                    for(int i=0; i<ap.getChildren().size(); i++){
                        if(!game.getMap().forTree.containsValue(ap.getChildren().get(i))) {
                            if (!game.getMap().forWater.containsValue(ap.getChildren().get(i))) {
                                String ss = ap.getChildren().get(i).getClass()+"";
                                if(!ss.equals(ee)) {
                                    if (ap.getChildren().get(i).contains(x, y+1)) {
                                        os++;
                                        if(game.getMap().forBrick.containsValue(ap.getChildren().get(i))){
                                            int re = ran.get(ap.getChildren().get(i));
                                            re++;
                                            Node no = ap.getChildren().get(i);
                                            if(re==4){
                                                ap.getChildren().remove(no);
                                                ran.remove(no);
                                            }else{
                                                ran.remove(no);
                                                ran.put( no , re);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(os==0){
                        y = y+2;
                        pulya.setCenterY(y);
                    }else{
                        stop();
                        getChildren().remove(pulya);
                    }
                    break;
                case 270:
                    int tr = 0;
                    for(int i=0; i<ap.getChildren().size(); i++){
                        if(!game.getMap().forTree.containsValue(ap.getChildren().get(i))) {
                            if (!game.getMap().forWater.containsValue(ap.getChildren().get(i))) {
                                String ss = ap.getChildren().get(i).getClass()+"";
                                if(!ss.equals(ee)) {
                                    if (ap.getChildren().get(i).contains(x-1, y)) {
                                        tr++;
                                        if(game.getMap().forBrick.containsValue(ap.getChildren().get(i))){
                                            int re = ran.get(ap.getChildren().get(i));
                                            re++;
                                            if(re==4){
                                                ap.getChildren().remove(ap.getChildren().get(i));
                                                ran.remove(ap.getChildren().get(i));
                                            }else{
                                                ran.remove(ap.getChildren().get(i));
                                                ran.put(ap.getChildren().get(i), re);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(tr==0){
                        x = x-2;
                        pulya.setCenterX(x);
                    }else{
                        stop();
                        getChildren().remove(pulya);
                    }
                    break;
                default:
                    break;
            }
    }
}