//package com.team2.questionme.service;
//
//import com.team2.questionme.dto.AddAnswerDTO;
//import com.team2.questionme.dto.AddCommentDTO;
//import com.team2.questionme.dto.AddQuestionDTO;
//import com.team2.questionme.model.Answer;
//import com.team2.questionme.model.Comment;
//import com.team2.questionme.model.Question;
//import com.team2.questionme.model.User;
//import com.team2.questionme.repository.AnswerRepository;
//import com.team2.questionme.repository.CommentRepository;
//import com.team2.questionme.repository.QuestionRepository;
//import com.team2.questionme.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//
//@Service
//public class CommentService {
//
//    private QuestionRepository questionRepository;
//    private UserRepository userRepository;
//    private AnswerRepository answerRepository;
//    private CommentRepository commentRepository;
//
//    public CommentService(QuestionRepository questionRepository, UserRepository userRepository, AnswerService answerService, CommentService commentService) {
//        this.questionRepository = questionRepository;
//        this.userRepository = userRepository;
//        this.answerRepository = answerRepository;
//        this.commentRepository = commentRepository;
//    }
//
//    public void addComment(AddCommentDTO addCommentDTO, AddQuestionDTO addQuestionDTO, AddAnswerDTO addAnswerDTO) {
//        User testUser = new User("TestName","TestPassword","test@com","test Display Name");
//        userRepository.save(testUser);
//        Question question = new Question(testUser,addQuestionDTO.getContent(), LocalDate.now(),addQuestionDTO.getCategory());
//        questionRepository.save(question);
//        Answer answer = new Answer(1L,testUser, question, addAnswerDTO.getContent(), LocalDate.now());
//        answerRepository.save(answer);
//        Comment comment = new Comment()
//    }
//}
