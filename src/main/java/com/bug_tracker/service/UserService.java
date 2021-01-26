package com.bug_tracker.service;

import com.bug_tracker.exceptions.custom.DaoException;
import com.bug_tracker.model.User;
import com.bug_tracker.repository.spring_data.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static com.bug_tracker.constants.ExceptionMessageConstants.CAN_NOT_UPDATE_USER;
import static com.bug_tracker.constants.ExceptionMessageConstants.NO_SUCH_USER_ID;
import static com.bug_tracker.constants.LoggingConstants.*;

@Service
public class UserService {

    private UserRepository userRepo;
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User create(User user) {
        logger.debug(CREATE_USER_MESSAGE);
        return userRepo.save(user);
    }

    public User findById(long id) {
        logger.debug(FIND_USER_MESSAGE + id);
        return userRepo.findById(id).orElseThrow(() -> {
            logger.warn(NO_SUCH_USER_ID + id);
            return new NoSuchElementException(NO_SUCH_USER_ID + id);
        });
    }

    public List<User> findAll() {
        logger.debug(FIND_ALL_USES_MESSAGE);
        return userRepo.findAll();
    }


    public User update(User user) {
        logger.debug(UPDATE_USER_MESSAGE + user.getId());
        try {
            return userRepo.save(user);
        } catch (DataAccessException e) {
            logger.warn(CAN_NOT_UPDATE_USER + user.getId() + e.getMessage());
            throw new DaoException(CAN_NOT_UPDATE_USER + user.getId());
        }
    }

    public void delete(long id) {
        userRepo.deleteById(id);
    }

}
