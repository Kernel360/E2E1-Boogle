package com.kernel360.boogle.global.batch;


import com.kernel360.boogle.book.db.BookEntity;
import com.kernel360.boogle.book.db.BookRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

@Component
//@AllArgsConstructor
public class MailForNewRelease {
    @Value("${mail.batch.email}")
    private String user_email;

    @Value("${mail.batch.password}")
    private String user_pw;
    static final String smtp_host = "smtp.gmail.com";
    static final int tls_port = 587;

    private final BookRepository bookRepository;

    public MailForNewRelease(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void Send() throws Exception {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtp_host);
        props.put("mail.smtp.port", tls_port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.ssl.protocols","TLSv1.2");
        props.put("jdk.tls.client.protocols", "TLSv1.2");
        props.put("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");

        System.out.println("USER_EMAIL>>"+user_email);
        // 메일 세션 생성
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user_email, user_pw);
                    }
                });
        try {

            // 메일 메시지 생성
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user_email));


            String[] recipientAddresses = {
                    "kyl8555@gmail.com",
                    "bighitstory@kakao.com",
                    "2018190529@korea.ac.kr",
                    "rosskysoss@gmail.com"
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
