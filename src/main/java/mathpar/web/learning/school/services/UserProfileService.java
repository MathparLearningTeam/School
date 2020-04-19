package mathpar.web.learning.school.services;

import mathpar.web.learning.school.entities.School;
import mathpar.web.learning.school.entities.UserProfile;
import mathpar.web.learning.school.repositories.UserProfileRepository;
import mathpar.web.learning.school.utils.enums.Role;
import mathpar.web.learning.school.utils.exceptions.MalformedDataException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserProfileService {
    private final AccountService accountService;
    private final UserProfileRepository userProfileRepository;

    public UserProfileService(AccountService accountService, UserProfileRepository userProfileRepository) {
        this.accountService = accountService;
        this.userProfileRepository = userProfileRepository;
    }

    public List<UserProfile> getProfiles(long accountId){
        return userProfileRepository.findAllByAccountId(accountId);
    }

    public UserProfile createProfile(long accountId, School school, Role role){
        return userProfileRepository.save(UserProfile.of(accountId, school, role));
    }

    public UserProfile createProfile(String email, School school, Role role){
        var accountId = accountService.getAccountId(email).orElseGet(()->accountService.createTemporaryAccount(email));
        return createProfile(accountId, school, role);
    }

    public Role getUserType(long userId){
        return userProfileRepository.getUserType(userId);
    }

    public UserProfile getProfile(long profileId){
        return userProfileRepository.findById(profileId).orElseThrow(MalformedDataException::new);
    }

    public UserProfile getDirector(School school){
        return userProfileRepository.findBySchoolAndRole(school, Role.Director).get(0);
    }

    @Transactional
    public void removeAllSchoolProfiles(long schoolId){
        userProfileRepository.deleteBySchool(new School(schoolId));
    }
}
