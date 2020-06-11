package application.mail;

import application.model.Paciente;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

import static application.utils.MailUtils.mail;
import static application.utils.MailUtils.pass;
import static javax.mail.Transport.send;
import static javax.mail.internet.InternetAddress.parse;

public class Mail {

    public static void sendMail(Paciente paciente, List<String> prescricoes) {
        sendMail(paciente.getEmail(), prescricoes);
    }
    
    public static void sendMail(String emailPaciente, List<String> prescricoes) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mail, pass);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail));

            Address[] toUser = parse(emailPaciente);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Prescrições da consulta");
            message.setText(montarTexto(prescricoes));
            send(message);

            System.out.println("Email enviado com sucesso!");

        } catch (MessagingException e) {
            System.err.println("Erro ao enviar o email");
            e.printStackTrace();
        }
    }

    private static String montarTexto(List<String> prescricoes) {
        StringBuilder sb = new StringBuilder("Segue a lista de prescrições da consulta:\n");
        prescricoes.forEach(p -> sb.append("- ".concat(p).concat("\n")));
        return sb.toString();
    }
}
