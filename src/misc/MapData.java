package misc;

import model.Food;
import model.Bomb;
import model.Teleport;

import java.awt.*;
import java.util.ArrayList;

public class MapData {

	
	
	private static MapData sinMap = null;
    public int x;
    public int y;
    public int[][] map;
    public Point pacmanPosition;
    public Point ghostBasePosition;
    public boolean isCustom;
    public ArrayList<Food> foodPositions;
    public ArrayList<Bomb> Bombs;
    public ArrayList<Teleport> teleports;
    public ArrayList<GhostData> ghostsData;

    public MapData(){
        foodPositions = new ArrayList<>();
        Bombs = new ArrayList<>();
        teleports = new ArrayList<>();
        ghostsData = new ArrayList<>();
    }
    
    
    

    public MapData(int x, int y){
        this.x = x;
        this.y = y;

        foodPositions = new ArrayList<>();
        Bombs = new ArrayList<>();
        teleports = new ArrayList<>();
        ghostsData = new ArrayList<>();
    }

    public MapData(int x, int y, int[][] map, Point pacPosition){
        this.x = x;
        this.y = y;
        this.map = map;
        pacmanPosition = pacPosition;

        foodPositions = new ArrayList<>();
        Bombs = new ArrayList<>();
        teleports = new ArrayList<>();
        ghostsData = new ArrayList<>();
    }
    
    	
    

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public Point getPacmanPosition() {
        return pacmanPosition;
    }

    public void setPacmanPosition(Point pacmanPosition) {
        this.pacmanPosition = pacmanPosition;
    }

    public Point getGhostBasePosition() {
        return ghostBasePosition;
    }

    public void setGhostBasePosition(Point ghostBasePosition) {
        this.ghostBasePosition = ghostBasePosition;
    }

    public ArrayList<Food> getFoodPositions() {
        return foodPositions;
    }

    public ArrayList<Bomb> getpufoodPositions() {
        return Bombs;
    }

    public ArrayList<Teleport> getTeleports() {
        return teleports;
    }

    public ArrayList<GhostData> getGhostsData() {
        return ghostsData;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }


	public static MapData getSinMap() {
		if(sinMap == null) {
			 sinMap  = new MapData();
			return sinMap;
		}
		return sinMap;
	}






	public static void setSinMap(MapData sinMap) {
		MapData.sinMap = sinMap;
	}
}
