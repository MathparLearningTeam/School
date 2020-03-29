package mathpar.web.learning.school.controllers.api;

import io.swagger.annotations.Api;
import mathpar.web.learning.school.entities.UserProfile;
import mathpar.web.learning.school.services.UserProfileService;
import mathpar.web.learning.school.utils.PublicApi;
import mathpar.web.learning.school.utils.ResponseMapper;
import mathpar.web.learning.school.utils.SecurityUtils;
import mathpar.web.learning.school.utils.dto.payloads.CreateProfilePayload;
import mathpar.web.learning.school.utils.dto.responses.UserProfileResponse;
import org.springframework.web.bind.annotation.*;

import static mathpar.web.learning.school.utils.SchoolUrls.PROFILE_URL;

@PublicApi
@RestController
@Api(tags = "Profile")
public class ProfileController {
    private final UserProfileService userProfileService;

    public ProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping(PROFILE_URL)
    public UserProfileResponse getProfile(@RequestParam(value = "profileId", required = false, defaultValue = "-1") long profileId){
        UserProfile profile;
        if (profileId<0) profile = SecurityUtils.getUserAuthentication().getDetails().getProfile();
        else profile = userProfileService.getProfile(profileId);
        return ResponseMapper.mapProfileToResponse(profile, false);
    }

    @PostMapping(PROFILE_URL)
    public UserProfileResponse createProfile(@RequestBody CreateProfilePayload payload){
        var authentication = SecurityUtils.getUserAuthentication().getDetails();
        var school = authentication.getSchool();
        return ResponseMapper.mapProfileToResponse(userProfileService.createProfile(payload.getEmail(), school, payload.getRole()), false);
    }
}
