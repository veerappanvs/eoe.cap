package edu.mass.doe.cap.dataservice.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * Observation Group Link entity to store the multiple selection of group size  
 * @author vsubramaniyan
 *
 */


@Entity
@Table(name = "CP081OGL_OBS_GROUP_LINK")
@AttributeOverrides({
@AttributeOverride(name = "createDate", column = @Column(name = "OGL_CREATE_DATE")),
@AttributeOverride(name = "createUser", column = @Column(name = "OGL_CREATED_BY")),
@AttributeOverride(name = "modifyDate", column = @Column(name = "OGL_UPDATE_DATE")),
@AttributeOverride(name = "modifyUser", column = @Column(name = "OGL_UPDATED_BY"))
})
public class ObservationGroupLink extends BaseJPAEntity {

	
	public ObservationGroupLink() {
		super();
	}

	public ObservationGroupLink(Long observationId, String groupCode, Date effDate) {
		super();
		this.observationId = observationId;
		this.groupCode = groupCode;
		this.effDate = effDate;
	}

	@Id
	@SequenceGenerator(name = "OGL_PK_GENERATOR", sequenceName = "CP081OGL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OGL_PK_GENERATOR")
	@Column(name="OGL_LINK_ID")	
	private Long  groupLinkId;
	
	@Column(name="OGL_OBSERVATION_ID")	
	private Long observationId;
	
	@Column(name="OGL_OBS_GROUP_CODE")	
	private String groupCode;
	
	@Column(name="OGL_EFF_DATE")	
	private Date effDate;
	
	@Column(name="OGL_EXP_DATE")	
	private Date expDate;

	public Long getGroupLinkId() {
		return groupLinkId;
	}

	public void setGroupLinkId(Long groupLinkId) {
		this.groupLinkId = groupLinkId;
	}

	public Long getObservationId() {
		return observationId;
	}

	public void setObservationId(Long observationId) {
		this.observationId = observationId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}


}
