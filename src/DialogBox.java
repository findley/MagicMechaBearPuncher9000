import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * General Class for instantiating Dialog Boxes.
 * 
 * To use this class, another class can instantiate dialog boxes and put them in a
 * list or an array. Then, in both the render and update method of that class, iterate
 * through the list of dialog boxes, calling DialogBox.render(container, graphic) or 
 * DialogBox.update(container, delta) as appropriate. 
 * 
 * Furthermore, the class that instantiates a dialog box must pass in a Unicode Font that has
 * glyphs and color effects added. I assume that the GameState will generally be the one to
 * instantiate boxes, so this is fine, but fonts take much time to create on the spot, so it
 * is important that the class that instantiates a dialog box have a preloaded, or pre-passed-in
 * font to use. 
 * 
 * Also, in the spirit of giving credit where it is due, this code comes from the user davedes
 * from the website: http://slick.javaunlimited.net/viewtopic.php?t=3778. 
 * I will delete this text as I rewrite the code as necessary.
 * @author Sonokin
 *
 */
public class DialogBox{
	
	private final int x;
	private final int y;
	private int boxIndex = 0;
	
	private List<String> dialog;
	private List<String> boxes;
	
	private UnicodeFont font;
	private int width = 600;
	private int height = 200;
	private int padding = 5;
	private Color box = new Color(1f,1f,1f,0.45f);
	
	private int numLinesRendered = 0;
	private int numCharactersRendered = 0;
	   
	private final static int REGULAR_DELAY = 50;
	private final static int QUICK_DELAY = 10;
	private int delay = REGULAR_DELAY;
	private int time = delay;
	private boolean finished = false;
	
	private int quickTextKey = Input.KEY_DOWN;
	private int proceedTextKey = Input.KEY_SPACE;
	
	/**
	 * Constructor for creating the dialog box.
	 * Incoming string is converted to List<String> object for use in wrapping.
	 * 
	 * @param x: X Coordinate for dialog box.
	 * @param y: Y Coordinate for dialog box.
	 * @param text: String that will be shown in dialog box.
	 * @param font: Font that the dialog box will use to render the text.
	 * @param key: Key that, when pressed will accelerate the text rendering.
	 */
	public DialogBox(int x, int y, String text, UnicodeFont font, int speedKey, int proceedKey){
		
		this.x = x;
		this.y = y;
		this.font = font;
		quickTextKey = speedKey;
		proceedTextKey = proceedKey;
		boxes = parseDialog(text, '-');
		dialog = wrap(boxes.get(boxIndex), font, width);
	}
	/**
	 * Main function that renders the dialog box and displays the text
	 * in a typewriter-like fashion. The text box only renders as many lines
	 * as can fit within the text box, and thus there is a scrolling effect for
	 * long text.
	 * 
	 * @param container
	 * @param g
	 */
	public void render(GameContainer container, Graphics g){
		int lineLocation = this.y;
		
        g.setColor(box);
        g.fillRect(x-padding, y-padding, width+padding*2, height+padding*2);
        g.setColor(Color.white);
        
        int lineHeight = font.getLineHeight();
        int maxLines = height/lineHeight;
        int startRender = Math.max(0, numLinesRendered-maxLines+1);
       
        //only render the rows we have typed out so far (renderRow = current row)
        for (int i=startRender; i<= numLinesRendered; i++) {
            String line = dialog.get(i);
            //render whole line if it's a previous one, otherwise render the column
            int len;
            if (i < numLinesRendered){
            	len = line.length();
            }
            else{
            	len = numCharactersRendered;
            }
            String t = line.substring(0, len);
            if (t.length()!=0) {
            	font.drawString(x, lineLocation, t, Color.white);
            }
            lineLocation += lineHeight;
        }
	}
	
	/**
	 * Method that updates the typewriter effect
	 * 
	 * @param container
	 * @param delta
	 * @throws SlickException
	 */
    public void update(GameContainer container, int delta) throws SlickException {
        time -= delta;
        keyPressed(container);
        if (time<=0 && !finished) {
            time = delay;
        
	        //Check to see if we should move down to next line. 
	        if (numCharactersRendered > dialog.get(numLinesRendered).length()-1){
	        	//Everything has been rendered
	        	if (numLinesRendered >= dialog.size()-1){
	        		finished = true;
	        	}
	        	else{//Move to next line
	        		numLinesRendered++;
	        		numCharactersRendered = 0;
	        	}
	        }
	        else{//move to the next character
	        	numCharactersRendered++;
	        }
        }
    }
   
    /**
     * Splits the given string into a list of lines that will be used
     * to create the wrapping effect of the text in the dialog box.
     * 
     * @param text: String to be wrapped.
     * @param font: Font whose dimensions are used to split the lines.
     * @param width: Length of dialog box used to determine where to end lines.
     * @return: ArrayList of split strings.
     */
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
    private List<String> parseDialog(String dialog, char demarcation){
    	List<String> boxes  = new ArrayList<String>();
    	int j = 0;
    	for (int i = 0; i <dialog.length(); i++){
    		if (dialog.charAt(i) == demarcation){
    			String box = dialog.substring(j, i);
    			j = i+1;
    			boxes.add(box);
    		}
    	}
    	return boxes;
    }
    private void proceed(){
    	finished = false;
    	numCharactersRendered = 0;
    	numLinesRendered = 0;
    	boxIndex++;
    	dialog = wrap(boxes.get(boxIndex),font,width);
    	
    	
    	
    }
    /**
     * Method that takes whatever input the container is currently receiving and compares 
     * it to the key that has been designated for speeding up text. 
     * 
     * @param c
     */
    private void keyPressed(GameContainer c){
    	Input key = c.getInput();
    	if (key.isKeyDown(quickTextKey)){
    		delay = QUICK_DELAY;
    	}
    	else if (key.isKeyPressed(proceedTextKey)){
    		proceed();
    	}
    	else{
    		delay = REGULAR_DELAY;
    	}
    	
    }
}
