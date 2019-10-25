package edu.mass.doe.cap.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import edu.mass.doe.cap.security.EOEUser;

/**
 * The Class CapUtil.
 */
@Component
public class CapUtil {

	/**
	 * Checks if is admin.
	 *
	 * @return true, if is admin
	 */
	public static boolean isAdmin() {

		boolean isAdmin = false;

		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();

		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
				isAdmin = true;
				break;
			}
		}

		return isAdmin;
	}

	/**
	 * Checks if is manager.
	 *
	 * @return true, if is manager
	 */
	public static boolean isManager() {

		boolean isManager = false;

		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();

		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_MANAGER")) {
				isManager = true;
				break;
			}
		}

		return isManager;
	}

	/**
	 * Checks if is supervisor.
	 *
	 * @return true, if is supervisor
	 */
	public static boolean isSupervisor() {

		boolean isSupervisor = false;
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();

		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_SUPERVISOR")) {
				isSupervisor = true;
				break;
			}
		}
		return isSupervisor;
	}

	/**
	 * Checks if is practitioner.
	 *
	 * @return true, if is practitioner
	 */
	public static boolean ispractitioner() {

		boolean ispractitioner = false;

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();

		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_PRACTITIONER")) {
				ispractitioner = true;
				break;
			}
		}
		return ispractitioner;
	}

	/**
	 * Checks if is candidate.
	 *
	 * @return true, if is candidate
	 */
	public static boolean isCandidate() {

		boolean isCandidate = false;

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();

		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_CANDIDATE")) {
				isCandidate = true;
				break;
			}
		}

		return isCandidate;
	}
	
	
	public static EOEUser getUserDetails() {
		EOEUser userContext = (EOEUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 return userContext;
		
	}
	
	/**
	 * Sort by value.
	 *
	 * @param <K> the key type
	 * @param <V> the value type
	 * @param unsortMap the unsort map
	 * @return the map
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> unsortMap) {

        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<K, V>>()  {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;

    }
}
