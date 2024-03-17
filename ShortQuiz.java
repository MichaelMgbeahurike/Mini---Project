/* ***************************************
  @author    Michael Mgbeahurike
  @SID       230332704
  @date      17 October 2023
  @version   1

    Miniproject Level 8
    A short quiz which only contains only 2
    questions and has point system as well
    and only two users can 
   ****************************************/
import java.io.*;
// Creates variables
//
class UserInformation
{
    String User;
    int Points;
} // END UserInformation

class QnA
{
    String Question;
    String Answer;
}//END QnA

class ShortQuiz
{
    public static void main (String [] a) throws IOException
    {
        Quiz1();
        return;
    } // END main

    //Writes the questions by creates .txt file.
    public static void WriteQuestions() throws IOException
    {
        PrintWriter input_Questions = new PrintWriter (new FileWriter("questions.txt"));
        input_Questions.println("What are the two type of variables, if global variable is given.");
        input_Questions.println("LOCAL");
        input_Questions.println("What is the word we break down a program into a bit by bit.");
        input_Questions.println("ABSTRACTION");
        input_Questions.close();
    }//END WriteQuestions
    //The Method creates record and assigns empty string to the fields.
    public static QnA CreateQnA() 
    {
        QnA l = new QnA();
    
        l.Question = "";
        l.Answer = "";
    
        return l;
    }//ENDS CreateQnA
    //The method outputs the message and asks for the user to input something.
    public static String InputString(String Message)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Message);
        String input = scanner.nextLine();
        return input;
    }//END InputString
    //The method creates a record and calls the other record and returns the record
    public static QnA addQuestion(QnA Quest,String QuestionNo,String Ans)
    {
        Quest = CreateQnA();
        Quest = setName(Quest, QuestionNo);
        Quest = setAns(Quest, Ans);
        return Quest;
    }//END addQuestion
    //The method assigns a field and returns the record.
    public static QnA setName(QnA Q, String var)
    {
        Q.Question = var;
        return Q;
    }//END setName
    //The method assigns a field and returns the record.    
    public static QnA setAns(QnA Q, String var)
    {
        Q.Answer = var;
        return Q;
    }//END setAns

    //The method returns a field from a record.
    public static String getQuestion(QnA Q)
    {
        return Q.Question;
    }//END getQuestion
    
    //The method returns a field from a record. 
    public static String getAns(QnA Q)
    {
        return Q.Answer;
    }//END getAns
    //The method read the csv file and place in a record array and checks if the they got it correct.
    public static int ReadingQuestions(int AmountQuestions, int Question,int PointSystem) throws IOException
    {
 
        final int Lines = AmountQuestions *2;
        String Questionth = "";
        String Answerth = "";
        QnA [] ListofQuestions = new QnA [Lines];
        BufferedReader ReadQuestion = new BufferedReader (new FileReader("questions.txt"));
        for (int i = 0; i < Lines; i++)
        {
            Questionth = ReadQuestion.readLine();
            Answerth = ReadQuestion.readLine();
            ListofQuestions[i] = addQuestion(ListofQuestions[i], Questionth, Answerth);
        }
        String Userinput;
        Userinput = InputString(getQuestion(ListofQuestions[(Question-1)]));
        Userinput = Userinput.toUpperCase();
        if (Userinput.equals(getAns(ListofQuestions[((Question-1))])))
        {
            PointSystem = PointSystem + 1;
        }
        else
        {
            if (Question == 1)
            {
                System.out.println("That is Incorrect. The correct answer was local varaible, which stores data in methods only.");
            }
            else if (Question == 2)
            {
                System.out.println("That is Incorrect. The correct answer was abstraction");
            }
        }
            

        return PointSystem;
    } //END ReadingQuestion

    // Creates Inferace where users can see what they got.
    //
    public static void Intereface(int NumberofPlayers,int [] Points)
    {
        final int PointPerson =  2;
        for (int i = 0; i <= NumberofPlayers-1; i ++)
        {
            String Message = "User"+(i+1)+" | ";
            for (int j = 0; j <= PointPerson-1; j ++)
            {
                Message=Message+Points[(i*2)+j]+" | ";
            }
            System.out.println(Message);
        }
        return; 
    } // END UserInformation

    // Asks for response for the secondquestion
    //
    public static int Questions2 ()
    {
        String Answer2;
        int Point = 0;
        Scanner scanner = new Scanner(System.in);
        final String message = "What is the word we break down a program into a bit by bit.";
        System.out.println(message);
        Answer2 = scanner.nextLine();
        Answer2= Answer2.toUpperCase();
        if (ChecktheAnswer(Answer2, "ABSTRACTION"))
        {
            System.out.println("That is Correct");
            Point = 1;
        }
        else
        {
            System.out.println("That is Incorrect. The correct answer was abstraction");
        }
        
        return Point;
    } // END Questions2

    // Checks if the user got the answer correct or wrong
    //
    public static int CorrectBoolean(int PreviousCorrect, int Correct,int [] Points)
    {
        if (PreviousCorrect < Correct && Correct != 0)
        {
            return 1;
        }
        return 0;
    } // END CorrectBoolean
    
    // Adds up all correct and answers and asks the user how many people are playing
    //
    public static void PlayerSystem () throws IOException
    {
        WriteQuestions();
        UserInformation User1 = new UserInformation();
        UserInformation User2 = new UserInformation();
        final int AmountofQuesitons = 2;
        String NumberofPlayers;
        int NumberofPlays;
        int PointSystem = 0;
        int Placement = 0;
        int PreviousCorrect = 0;
        int Questions = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many people would you like to play?(Max 2 players)");
        NumberofPlayers = scanner.nextLine();
        NumberofPlays = Integer.parseInt(NumberofPlayers);
        while (NumberofPlays>3)
        {
            NumberofPlayers = scanner.nextLine();
            NumberofPlays = Integer.parseInt(NumberofPlayers);
        }
        System.out.println("What would like your username to be Player1?");
        User1.User = scanner.nextLine();
        if (NumberofPlays == 2)
        {
            System.out.println("What would like your username to be Player2?");
            User2.User = scanner.nextLine();
        }
        int ArrayLength = AmountofQuesitons*NumberofPlays;
        int [] ArrayPoint = new int [ArrayLength];
        for (int i = 1; i <= NumberofPlays; i++)
        {
            PreviousCorrect = 0;
            Questions =1;
            PointSystem = 0;
            System.out.println("Player "+ i+" is your turn");
            PointSystem = ReadingQuestions(AmountofQuesitons,Questions,PointSystem);
            //PointSystem = Question1() + PointSystem;
            Questions = Questions + 1;
            ArrayPoint[Placement] = CorrectBoolean(PreviousCorrect, PointSystem, ArrayPoint);
            PreviousCorrect = PointSystem;
            PointSystem = ReadingQuestions(AmountofQuesitons, Questions,PointSystem);
            //PointSystem = Questions2() + PointSystem;
            Placement = Placement +1;
            ArrayPoint[Placement] = CorrectBoolean(PreviousCorrect, PointSystem, ArrayPoint);
            if (i==1)
            {
                User1.Points=PointSystem;
            }
            else
            {
                User2.Points=PointSystem;
            }
            System.out.println("Here is your score " + PointSystem);
            Placement = Placement + 1;
        }
        for (int i = 0; i < 4; i++)
        {
            System.out.println(ArrayPoint[i]);
        }
        Intereface(NumberofPlays,ArrayPoint);
    } // END PlayerSystem
    
    
   // Check if the arguments are both the same
    //
    public static boolean ChecktheAnswer (String UserA, String Answer)
    {
        if (UserA.equals(Answer))
        {
            return true;
        }
        else
        {
            return false;
        }
    
    } // END ChecktheAnswer

    // Asks for response for the first question
    //
    public static int Question1 ()
    {
        String Answer1;
        int Point = 0;
        Scanner scanner = new Scanner(System.in);
        final String message2 = "What are the two type of variables, if global variable is given.";
        System.out.println(message2);
        Answer1 = scanner.nextLine();
        Answer1= Answer1.toUpperCase();  //makes all the character a upper case
        if (ChecktheAnswer(Answer1, "LOCAL"))
        {
            System.out.println("That is Correct");
            Point = 1;
        }
        else
        {
            System.out.println("That is Incorrect. The correct answer was local varaible, which stores data in methods only.");
        }
        
        return Point;
    } // END Question1
    // Print ahello message and the rules
    //
    public static void WelcomeRules ()
    {
         System.out.println("=============================================");
         System.out.println("Hello there user");
         System.out.println("Welcome to the Short Computer Science Quiz");
         System.out.println("Here are the rules list below.");
         Rules();
         System.out.println("=============================================");
         return;
    } // END WelcomeRules

    //Prints the rules for the quiz.
    //
    public static void Rules ()
    {
        System.out.println("Rule 1 : Do not look up information on the internet");
        System.out.println("Rule 2 : Do not ask anyone for help");
        System.out.println("Rule 3 : Have fun and enjoy");
        return;
    } // END Rules
        
    
    // Asks the users if the user would like to join the quiz
    //
    public static void Agreement () throws IOException
    {
         String Agree1;
         String Agree2;
         Scanner scanner = new Scanner(System.in);
         System.out.println("=============================================");
         System.out.println("Would you like to join the short quiz?");
         Agree1 = scanner.nextLine();
         Agree1 = Agree1.toUpperCase();
         System.out.println("Is this you answer "+Agree1+ ".");
         Agree2 = scanner.nextLine();
         Agree2 = Agree2.toUpperCase();
         System.out.println("Processing...");
         System.out.println("=============================================");
         if ((Agree1.equals("YES")) && (Agree2.equals("YES")))
         {
             PlayerSystem();
         }    
         return;
    } // END Agreement

    
    
    // A short quiz program - prints a welcome message, asks the user if the user would like to play the game.
    //
    public static void Quiz1 () throws IOException
    {
         WelcomeRules();
         Agreement();
         return;
    } // END Quiz1
    
} // END class ShortQuiz