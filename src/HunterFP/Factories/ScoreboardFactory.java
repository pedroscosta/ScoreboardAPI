package HunterFP.Factories;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;


public class ScoreboardFactory {

    public static Scoreboard generateScoreboard(String Title, String... lines  ){
        return new Scoreboard(Title, lines);
    }

   public static class Scoreboard {

        private HashMap<Integer, String> lines = new HashMap<Integer, String>();
        private String title;
        private org.bukkit.scoreboard.Scoreboard scoreboard;
        private Objective objective;

        public Scoreboard(String Title, String... lines  ){

            this.title = Title;


            ScoreboardManager manager = Bukkit.getScoreboardManager();
            this.scoreboard = manager.getNewScoreboard();
            this.objective = this.scoreboard.registerNewObjective(this.title, "dummy");



            this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            int key = 15;
            for (String s : lines ) {
                this.lines.put(key,s);
                key--;
            }

            update();


        }

        public void setLine(int line, String text){
            this.lines.put(line,text);
            update();

        }

        public String getLine(int line){
            String lineString = this.lines.get(line);
            return lineString;
        }

        public void setTitle(String title){
            this.title = title;
            update();
        }

        public String getTitle(){
            return this.title;
        }

        public void setPlayer(Player p){
            p.setScoreboard(this.scoreboard);
        }

       public void update(){
           this.objective.setDisplayName(this.title);
           for(int key = 15; key > 0; key--){
                   String s = this.lines.get(key);

                   Score score = this.objective.getScore(s);
                   score.setScore(key);

           }
       }


    }


}
