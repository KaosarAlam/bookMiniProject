package com.miniProject.miniProjectTask.service;

import com.miniProject.miniProjectTask.entity.Book;
import com.miniProject.miniProjectTask.entity.History;
import com.miniProject.miniProjectTask.entity.User;
import com.miniProject.miniProjectTask.model.UserDto;
import com.miniProject.miniProjectTask.repo.BookRepo;
import com.miniProject.miniProjectTask.repo.HistoryRepo;
import com.miniProject.miniProjectTask.repo.UserRepo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserServiceImpl {

    @Autowired
    private UserRepo userRepository;
    @Autowired
    private BookRepo bookRepository;
    @Autowired
    private HistoryRepo historyRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private EntityManager entityManager;

    @Override
    public UserDto createUser(UserDto user) throws Exception {
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new Exception("Record already exists");

        ModelMapper modelMapper = new ModelMapper();
        User userEntity = new User();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        String publicUserId = JWTUtils.generateUserID(10);
        userEntity.setUserId(publicUserId);
        userEntity.setAddress(user.getAddress());
        userEntity.setRole(user.getRole());
        User storedUserDetails = userRepository.save(userEntity);
        UserDto returnedValue = modelMapper.map(storedUserDetails,UserDto.class);
        String accessToken = JWTUtils.generateToken(userEntity.getEmail());
        returnedValue.setAccessToken(AppConstants.TOKEN_PREFIX + accessToken);
        return returnedValue;
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserDto returnValue = new UserDto();
        User userEntity = userRepository.findByUserId(userId).orElseThrow(Exception::new);
        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Incorrect information"));
        return new User(userEntity.getEmail(),userEntity.getPassword(),
                true,true,true,true,new ArrayList<>());
    }

    @Override
    public List<Book> getBorrowedBook(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        Integer userId = user.get().getId();

        return bookRepository.getBookBorrowedByUser(userId);
    }

    @Override
    public List<Book> getCurrentBorrowedBook(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        Integer userId = user.get().getId();

        return bookRepository.getOccupiedBooksBorrowedByUser(userId);
    }

    public Optional<Book> borrowedBookById(Integer bookId, Date dueDate) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        Integer userId = user.get().getId();
        User userEntity = entityManager.find(User.class, userId);
        Book bookEntity = entityManager.find(Book.class, bookId);

        Optional<Book> bookEntityOptional = bookRepository.alreadyTakenBook(bookId);

        if(bookEntityOptional.isPresent()){
            throw new Exception();
        }
        else {
            History borrowedBookEntity = new History();
            borrowedBookEntity.setBorrowedDate(new Date());
            borrowedBookEntity.setBorrowedDate(dueDate);
            borrowedBookEntity.setStatus("occupied");
            borrowedBookEntity.setUserEntity(userEntity);
            borrowedBookEntity.setBookEntity(bookEntity);
            HistoryRepo.save(borrowedBookEntity);
            return bookEntityOptional;
        }
    }

    public void returnBook(Integer bookId){
        HistoryRepo.updateStatus(bookId);
    }
}