package earthquakes.services;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface MembershipService {

   /** check membership
     * @param oAuth2AuthenticationToken oauth token 
     * @return is current logged in user a member but NOT an admin of the github org?
     * */
    public boolean isMember(OAuth2AuthenticationToken oAuth2AuthenticationToken);

   /** check whether the user has admin access right
     * @param oAuth2AuthenticationToken oauth token 
     * @return is current logged in user an admin but NOT a member of the github org?
     * */
    public boolean isAdmin(OAuth2AuthenticationToken oAuth2AuthenticationToken);

   /** check whether is Admin or user 
     * @param oAuth2AuthenticationToken oauth token 
     * @return true if the current logged in user is an admin or a member of the github org, return false otherwise
     * */
    default public boolean isMemberOrAdmin(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return isMember(oAuth2AuthenticationToken) || isAdmin(oAuth2AuthenticationToken);
    }

   /** check whether is Admin or user 
     * @param token oauth token 
     * @return A string that indicates the user status, e.g. if the user token input is null then return "Guest". If the user is a Member of the github org, return "Member"
     * */ 
    default public String role(OAuth2AuthenticationToken token) {
        if (token==null)
            return "Guest";
        if (isMember(token))
            return "Member";
        if (isAdmin(token))
            return "Admin";
        return "Guest";
    }

}