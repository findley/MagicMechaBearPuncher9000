package framework;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

/** @author davedes */
public class TypewriterTest extends BasicGame {
   
    /**
     * Entry point to our test
     *
     * @param argv The arguments passed to the test
     */
    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer(new TypewriterTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
   
    private List<String> lines;
    private DialogBox[] dialogBoxes = new DialogBox[1];
    private UnicodeFont font;
    private int width = 400;
    private Color box = new Color(1f,1f,1f,0.45f);
   
    private int renderRow = 0;
    private int renderCol = 0;
   
    public static final int TYPE_DELAY = 50;
    private int time = TYPE_DELAY;
    private boolean finished = false;
   
    public TypewriterTest() {
        super("TypeWriter");   
    }
   
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ESCAPE) {
            System.exit(0);
        }  else if (key == Input.KEY_ENTER) {
        	ArrayList<String> text= new ArrayList<String>();
        	text.add("Why hello there, young adventurer!");
        	text.add("What? \nYou say you're not an adventurer?");
            text.add(
            		"Well of course you are! Just look how much you stand out " +
            		"from us regular folk!"
            		);
            text.add(
            		"You probably just don't know how to be adventurer! " +
            		"\nWhat? \nYou say could just read the instructions? \nNonsense I say! " +
            		"Here, let me explain how to move around! Just press the directional buttons! " +
            		"Okay, you probably figured that out on your own, so how about this? " +
            		"Did you know that while talking to people, you can press down to make them " +
            		"talk faster? Quite a handy trick, I'd say. Though, it won't work on me because I'm " +
            		"too old, but try it out on our villagers to see what I mean. But be warned! " +
            		"In times of great peril such as these, people often ask for pointless favors " +
            		"in return for slightly relevant aid toward your cause. It is not in your " +
            		"nature to refuse such requests, so I must implore you, talk with caution! " +
            		"T...time is...your most valua...ble ally...use it wise...zzz...zzz..."
            		);
            DialogBox dialog = new DialogBox(100,100, 300, 50, font, Input.KEY_DOWN);
            dialogBoxes[0] = dialog;
            dialogBoxes[0].playMsg(text);
        }
    }
   
    //initialize the game and dialog box
    public void init(GameContainer c) throws SlickException {
        String text = "I want meat!";

        //create a list of lines based on the above text
        Font awtFont = new Font("Arial Monospaced", Font.BOLD, 18);
	    font = new UnicodeFont(awtFont);
	    font.getEffects().add(new ColorEffect(java.awt.Color.white));
	    font.addAsciiGlyphs();
	    try {
	        font.loadGlyphs();
	    } catch (SlickException ex) {
	       // Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
	    }
        lines = wrap(text, font, width);
        
        
    }
   
    //render the dialog box
    public void render(GameContainer container, Graphics g) throws SlickException {
        
        for (DialogBox d: dialogBoxes){
        	if (d != null){
        		d.render(container, g);
        	}
        }
    }
   
    //update the game logic and typewriting effect
    public void update(GameContainer container, int delta) throws SlickException {
        for (DialogBox d: dialogBoxes){
        	if (d != null){
        		d.update(container, delta);
        	}
        }
     
    }
   
    //shows ALL text
    public void showAll() {
        if (lines.isEmpty())
            renderRow = renderCol = 0;
        else {
            renderRow = lines.size()-1;
            renderCol = lines.get(renderRow).length();
        }
        finished = true;
    }

    //restarts typewriting effect
    public void restart() {
        renderCol = 0;
        renderRow = 0;
        time = TYPE_DELAY;
        finished = false;
    }

    //Wraps the given string into a list of split lines based on the width
    private List<String> wrap(String text, UnicodeFont font, int width) {
        //A less accurate but more efficient wrap would be to specify the max
        //number of columns (e.g. using the width of the 'M' character or something).
        //The below method will look nicer in the end, though.
       
        List<String> list = new ArrayList<String>();
        String str = text;
        String line = "";
       
        //we will go through adding characters, once we hit the max width
        //we will either split the line at the last space OR split the line
        //at the given char if no last space exists
       
        //while we still have text to check
        int i = 0;
        int lastSpace = -1;
        while (i<str.length()) {
            char c = str.charAt(i);
            if (Character.isWhitespace(c))
                lastSpace = i;
           
            //time to wrap
            if (c=='\n' || font.getWidth(line + c) > width) {
                //if we've hit a space recently, use that
                int split = lastSpace!=-1 ? lastSpace : i;
                int splitTrimmed = split;
               
                //if we are splitting by space, trim it off for the start of the
                //next line
                if (lastSpace!=-1 && split<str.length()-1) {
                   splitTrimmed++;
                }
               
                line = str.substring(0, split);
                str = str.substring(splitTrimmed);
               
                //add the line and reset our values
                list.add(line);
                line = "";
                i = 0;
                lastSpace = -1;
            }
            //not time to wrap, just keep moving along
            else {
                line += c;
                i++;
            }
        }
        if (str.length()!=0)
            list.add(str);
        return list;
    }
}
