package com.aman.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private final EmailService emailService;
    private final ContactMessageRepository contactMessageRepository;

    @Autowired
    public ContactService(EmailService emailService, ContactMessageRepository contactMessageRepository) {
        this.emailService = emailService;
        this.contactMessageRepository = contactMessageRepository;
    }

    public void submitContactMessage(ContactMessageDTO messageDTO) {
        ContactMessage message = new ContactMessage();
        message.setName(messageDTO.getName());
        message.setEmail(messageDTO.getEmail());
        message.setSubject(messageDTO.getSubject());
        message.setMessage(messageDTO.getMessage());
        
        contactMessageRepository.save(message);
        
        // Send notification email to admin
        emailService.sendContactNotification(message);
    }

    // ...existing code...
}
