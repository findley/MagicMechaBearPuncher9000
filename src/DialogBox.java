import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class DialogBox{
	
	private final int x;
	private final int y;
	
	private List<String> dialog;
	private UnicodeFont font;
	private int width = 400;
	private Color box = new Color(1f,1f,1f,0.45f);
	   
	private int renderRow = 0;
	private int renderCol = 0;
	   
	public static final int TYPE_DELAY = 50;
	private int time = TYPE_DELAY;
	private boolean finished = false;
	    
	public DialogBox(int x, int y, String text){
		
		this.x = x;
		this.y = y;
		Font awtFont = new Font("Arial Monospaced", Font.BOLD, 18);
		font = new UnicodeFont(awtFont);
		dialog = wrap(text, font, width);
	}
	public void render(GameContainer container, Graphics g){
		int x = this.x;
		int y = this.y;
		
        int pad = 5;
        g.setColor(box);
        g.fillRect(x-pad, y-pad, width+pad*2, 200+pad*2);
       
        //g.setFont(font);
        g.setColor(Color.white);
        
        int lineHeight = font.getLineHeight();
       
        //only render the rows we have typed out so far (renderRow = current row)
        for (int i=0; i<renderRow+1; i++) {
            String line = dialog.get(i);
            //render whole line if it's a previous one, otherwise render the col
            int len = i<renderRow ? line.length() : renderCol;
            String t = line.substring(0, len);
            if (t.length()!=0) {
            	g.drawString(t,x,y);
            	font.drawString(x, y, t, Color.white);
            }
            y += lineHeight;
        }
       
        g.drawString("SPACE to restart, ENTER to show all", 10,80);
	}
	
	//update the game logic and typewriting effect
    public void update(GameContainer container, int delta) throws SlickException {
        time -= delta;
        if (time<=0 && !finished) {
            time = TYPE_DELAY;
           
            //if we are moving down to the next line
            if (renderCol > dialog.get(renderRow).length()-1) {
                //we've rendered all characters
                if (renderRow >= dialog.size()-1) {
                    finished = true;
                }
                //move to next line
                else {
                    renderRow++;
                    renderCol = 0;
                }
            } else {
                //move to next character
                renderCol++;
            }
        }
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
        System.out.println(list.toString());
        return list;
    }
}
