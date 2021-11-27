package isep.dei.lapr2.algorithms;

import isep.dei.lapr2.model.ExternalEmailSender;

import java.io.*;

/**
 * Algorithm to send email to a file
 */
public class EmailSender implements ExternalEmailSender {

    /**
     * Sends email, writing it in a file
     * @param emailDestiny Email of Destination.
     * @param header Header of the email.
     * @param content Content of the email.
     */
    public void sendEmail(String emailDestiny, String header , String content)  {
        try {

            File file = new File("e-mail.txt");
            FileWriter fileWriter = new FileWriter(file,true);
            PrintWriter pw = new PrintWriter(fileWriter);
            if (file.length()==0) {
                pw.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
            String email = String.format("To: %s\n\nSubject:%s \n\nContent: \n%s\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------",emailDestiny,header,content);
            pw.println(email);
            pw.close();
        }catch(FileNotFoundException e){
            System.out.println("Email Error");
        } catch (IOException ex) {
            System.out.println("Email Error");
        }

    }

}
