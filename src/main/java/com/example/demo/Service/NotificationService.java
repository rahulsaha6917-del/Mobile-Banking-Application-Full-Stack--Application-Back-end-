package com.example.demo.Service;

import com.example.demo.Entity.Notification;
import com.example.demo.Repository.NotificationRepository;

import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;

    // Constructor Injection
    public NotificationService(NotificationRepository notificationRepository,
                               JavaMailSender mailSender) {
        this.notificationRepository = notificationRepository;
        this.mailSender = mailSender;
    }

    // Create notification
    public Notification createNotification(Long userId, String type, String message) {

        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setType(type);
        notification.setMessage(message);
        notification.setIsRead(false);

        return notificationRepository.save(notification);
    }

    // Send email
    public void sendEmail(String toEmail, String subject, String body) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        mailSender.send(mailMessage);
    }

    // Get unread notifications
    public List<Notification> getUnreadNotifications(Long userId) {

        return notificationRepository.findByUserIdAndIsReadFalse(userId);
    }

    // Mark notification as read
    public void markAsRead(Long notificationId) {

        Optional<Notification> notificationOptional =
                notificationRepository.findById(notificationId);

        if (notificationOptional.isPresent()) {

            Notification notification = notificationOptional.get();
            notification.setIsRead(true);

            notificationRepository.save(notification);

        } else {
            throw new RuntimeException("Notification not found with id: " + notificationId);
        }
    }
}