package com.kernel360.boogle.global.batch;


import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.db.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

@Component
public class MailForNewRelease {
    // Application.yaml에 인자(mail.batch.email)를 추가하여 아래 Annotation을 주석을 해제하세요.
    // @Value("${mail.batch.email}")
    private String userEmail;

    // Application.yaml에 인자(mail.batch.password)를 추가하여 아래 Annotation을 주석을 해제하세요.
    // @Value("${mail.batch.password}")
    private String userPassword;
    static final String smtpHost = "smtp.gmail.com";
    static final int tlsPort = 587;

    private final BookRepository bookRepository;

    public MailForNewRelease(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void send() throws Exception {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", tlsPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.ssl.protocols","TLSv1.2");
        props.put("jdk.tls.client.protocols", "TLSv1.2");
        props.put("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");

        // 메일 세션 생성
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userEmail, userPassword);
                    }
                });
        try {

            // 메일 메시지 생성
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userEmail));

            // 수신자 메일 목록을 아래에 작성
            String[] recipientAddresses = {
                "reciver@gmail.com"
            };

            InternetAddress[] recipientInternetAddresses = new InternetAddress[recipientAddresses.length];
            for (int i = 0; i < recipientAddresses.length; i++) {
                recipientInternetAddresses[i] = new InternetAddress(recipientAddresses[i]);
            }

            // 받는 이메일
            message.setRecipients(
                    Message.RecipientType.TO,
                    recipientInternetAddresses
            );

            //
            LocalDate today = LocalDate.now();
            int thisYear = today.getYear();
            int thisMonth = today.getMonth().getValue();

            // 제목
            message.setSubject(String.format("[%d년 %d월 신간도서알림]안녕하세요. Boogle팀 Ross입니다.", thisYear, thisMonth));
            List<BookEntity> monthlyBooks = bookRepository.findAllByPublishDateBetween(today.minusMonths(1), today);

            // 내용
            StringBuilder stringBuilder = new StringBuilder();
            for (BookEntity book: monthlyBooks) {
                stringBuilder
                        .append("제목: ").append(book.getTitle()).append(" ")
                        .append("지은이: ").append(book.getAuthor()).append(" ")
                        .append("발행일: ").append(book.getPublishDate()).append(" ")
                        .append("ISBN: ").append(book.getIsbn()).append("\n");
            }

            message.setText(stringBuilder.toString());

            // 메일 보내기
            Transport.send(message);

            System.out.println("메일 발송 완료!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
