/**
 * 
 */
package com.yash;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mitali
 *
 */
@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {

	/**
	 * @param empid
	 * @return
	 */
	// Employee findOne(Long empid);

}
