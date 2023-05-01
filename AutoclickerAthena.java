import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class AutoclickerAthena extends JFrame implements ActionListener
{
	private JLabel textX,textY,textD,textB;
	private JTextField timePushJPTF,timePullJPTF,timeBeforeLaunchJPTF, buttonJPTF;
	private JButton launchButton;
	private static double timePush;
    private static double timePull;
    private double timeBeforeLaunch;

    //____________MAIN________________________________________ConsoleLaunch_____________
	public static void main(String [] args)	{
		AutoclickerAthena JPFram = new AutoclickerAthena();
	}
    //__________________________________________________________________________________




	AutoclickerAthena()
	{
        //____________INFORMATIONS ECRAN (TAILLE/POSITION/OPTION FERMETURE)______________
        int widthScreen = 500;
        int heightScreen = 500;
		setBounds((1920-widthScreen)/2,(1080-heightScreen)/2,widthScreen,heightScreen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //_______________________________________________________________________________
		
        Container c;
		c=getContentPane();
		BorderLayout bl=new BorderLayout();
		c.setLayout(bl);
		JPanel p=new JPanel();
		p.setLayout(null);




        //____________DELAY___________________________________________________________
		textD= new JLabel("Temps avant lancement : ");
		textD.setBounds(10,20,200,20);
		p.add(textD);
        //----------------------------------
        timeBeforeLaunchJPTF=new JTextField(20);
		timeBeforeLaunchJPTF.setBounds(220,20,100,20);
		p.add(timeBeforeLaunchJPTF);
		//_______________________________________________________________________________
        //____________APPUIE___________________________________________________________
		textX= new JLabel("Temps appuyé sur le boutton : ");
		textX.setBounds(10,110,200,20);
		p.add(textX);
        //----------------------------------
        timePushJPTF=new JTextField(20);
		timePushJPTF.setBounds(220,110,100,20);
		p.add(timePushJPTF);
		//_______________________________________________________________________________
        //____________RELACHE___________________________________________________________
		textY= new JLabel("Temps rester relaché le boutton : ");
		textY.setBounds(10,150,200,20);
		p.add(textY);
		//----------------------------------
		timePullJPTF=new JTextField(20);
		timePullJPTF.setBounds(220,150,100,20);
		p.add(timePullJPTF);
		//_______________________________________________________________________________
        //____________BOUTON___________________________________________________________
		textB= new JLabel("Bouton : ");
		textB.setBounds(10,210,200,20);
		p.add(textB);
        //----------------------------------
        buttonJPTF=new JTextField(20);
		buttonJPTF.setBounds(220,210,100,20);
		p.add(buttonJPTF);
		//_______________________________________________________________________________


		Border border = BorderFactory.createRaisedBevelBorder();
		launchButton=new JButton("Launch");
		launchButton.setActionCommand("Launch");
		launchButton.addActionListener(this);
		launchButton.setBorder(border);
		launchButton.setBounds(380,380,75,50);
		
		p.add(launchButton);
		
		c.add(p, BorderLayout.CENTER);
		setVisible(true);
	}
	

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("click");
        timeBeforeLaunch = Double.parseDouble(timeBeforeLaunchJPTF.getText());
        try {
            Thread.sleep((int) timeBeforeLaunch*1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        autoclicker(timePushJPTF.getText(), timePullJPTF.getText(), buttonJPTF.getText());
    }    


	public static int mouseButton(String button){
		switch(button){
			case "1":
				return InputEvent.BUTTON1_DOWN_MASK;
			case "2":
				return InputEvent.BUTTON2_DOWN_MASK;
			case "3":
				return InputEvent.BUTTON3_DOWN_MASK;
			default:
				System.out.println("Bouton non correcte.");
				return 0;
		}	
	}


    public static void autoclicker(String timePushString, String timePullString,String buttonString){
        timePush = Double.parseDouble(timePushString);
        timePull = Double.parseDouble(timePullString);

		int button = mouseButton(buttonString);

        while(button!=0){
            try{
                Robot robotClicker = new Robot();
                robotClicker.mousePress(button);
                Thread.sleep((int) timePush*1000);
                robotClicker.mouseRelease(button);
                Thread.sleep((int) timePull*1000);
    
            }catch(Exception e){
                e.printStackTrace();
            }
        }
		System.out.println("System unlaunched.");
    }



}