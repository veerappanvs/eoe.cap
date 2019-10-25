package edu.mass.doe.cap.dataservice.batch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import edu.mass.doe.cap.dataservice.pojo.CapData;

/**
 * The Class BatchDataMapper.
 */
public class BatchDataMapper implements FieldSetMapper<CapData> {
						

	
	private int ORGANIZATION_NAME=0;
	private int CANDIDATE_MEPID=1;
	private int CANDIDATE_FIRST_NAME=2;
	private int CANDIDATE_LAST_NAME=3;
	private int CANDIDATE_EMAIL_ADDRESS=4;
	private int SP_NAME=5;
	private int SP_MEPID=6;
	private int SP_EMAIL_ID=7;
	private int PRACTICUM_DISTRICT=8;
	private int PRACTICUM_SCHOOL=9;
	private int PS_NAME=10;
	private int PS_EMAIL_ID=11;
	private int SCHOOL_YEAR=12;
	private int PROGRAM=13;
	private int F1A4Q=14;
	private int F1A4S=15;
	private int F1A4C=16;
	private int F1B2Q=17;
	private int F1B2S=18;
	private int F1B2C=19;
	private int F2A3Q=20;
	private int F2A3S=21;
	private int F2A3C=22;
	private int F2B1Q=23;
	private int F2B1S=24;
	private int F2B1C=25;
	private int F2D2Q=26;
	private int F2D2S=27;
	private int F2D2C=28;
	private int F4A1Q=29;
	private int F4A1S=30;
	private int F4A1C=31;
	private int S1A4Q=32;
	private int S1A4S=33;
	private int S1A4C=34;
	private int S1B2Q=35;
	private int S1B2S=36;
	private int S1B2C=37;
	private int S2A3Q=38;
	private int S2A3S=39;
	private int S2A3C=40;
	private int S2B1Q=41;
	private int S2B1S=42;
	private int S2B1C=43;
	private int S2D2Q=44;
	private int S2D2S=45;
	private int S2D2C=46;
	private int S4A1Q=47;
	private int S4A1S=48;
	private int S4A1C=49;
	private int READY_TO_TEACH=50;
	
	

			
	
	public BatchDataMapper() {}
	

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.file.mapping.FieldSetMapper#mapFieldSet(org.springframework.batch.item.file.transform.FieldSet)
	 */
	@Override
	public CapData mapFieldSet(FieldSet fieldSet) throws BindException {
		
		CapData capData = new CapData();
		capData.setOrganizationName(fieldSet.readString(ORGANIZATION_NAME));
		capData.setCandidateMEPID(fieldSet.readString(CANDIDATE_MEPID));
		capData.setCandidateFirstname(fieldSet.readString(CANDIDATE_FIRST_NAME));
		capData.setCandidateLastname(fieldSet.readString(CANDIDATE_LAST_NAME));
		capData.setCandidateEmailid(fieldSet.readString(CANDIDATE_EMAIL_ADDRESS));
		capData.setSpName(fieldSet.readString(SP_NAME));
		capData.setSpMEPID(fieldSet.readString(SP_MEPID));
		capData.setSpEmailid(fieldSet.readString(SP_EMAIL_ID));
		capData.setPracticumDistrict(fieldSet.readString(PRACTICUM_DISTRICT));
		capData.setPracticumSchool(fieldSet.readString(PRACTICUM_SCHOOL));
		capData.setPsName(fieldSet.readString(PS_NAME));
		capData.setPsEmailid(fieldSet.readString(PS_EMAIL_ID));
		capData.setSchoolYear(fieldSet.readString(SCHOOL_YEAR));
		capData.setProgram(fieldSet.readString(PROGRAM));
		capData.setF1a4q(fieldSet.readString(F1A4Q));
		capData.setF1a4s(fieldSet.readString(F1A4S));
		capData.setF1a4c(fieldSet.readString(F1A4C));
		capData.setF1b2q(fieldSet.readString(F1B2Q));
		capData.setF1b2s(fieldSet.readString(F1B2S));
		capData.setF1b2c(fieldSet.readString(F1B2C));
		capData.setF2a3q(fieldSet.readString(F2A3Q));
		capData.setF2a3s(fieldSet.readString(F2A3S));
		capData.setF2a3c(fieldSet.readString(F2A3C));
		capData.setF2b1q(fieldSet.readString(F2B1Q));
		capData.setF2b1s(fieldSet.readString(F2B1S));
		capData.setF2b1c(fieldSet.readString(F2B1C));
		capData.setF2d2q(fieldSet.readString(F2D2Q));
		capData.setF2d2s(fieldSet.readString(F2D2S));
		capData.setF2d2c(fieldSet.readString(F2D2C));
		capData.setF4a1q(fieldSet.readString(F4A1Q));
		capData.setF4a1s(fieldSet.readString(F4A1S));
		capData.setF4a1c(fieldSet.readString(F4A1C));
		capData.setS1a4q(fieldSet.readString(S1A4Q));
		capData.setS1a4s(fieldSet.readString(S1A4S));
		capData.setS1a4c(fieldSet.readString(S1A4C));
		capData.setS1b2q(fieldSet.readString(S1B2Q));
		capData.setS1b2s(fieldSet.readString(S1B2S));
		capData.setS1b2c(fieldSet.readString(S1B2C));
		capData.setS2a3q(fieldSet.readString(S2A3Q));
		capData.setS2a3s(fieldSet.readString(S2A3S));
		capData.setS2a3c(fieldSet.readString(S2A3C));
		capData.setS2b1q(fieldSet.readString(S2B1Q));
		capData.setS2b1s(fieldSet.readString(S2B1S));
		capData.setS2b1c(fieldSet.readString(S2B1C));
		capData.setS2d2q(fieldSet.readString(S2D2Q));
		capData.setS2d2s(fieldSet.readString(S2D2S));
		capData.setS2d2c(fieldSet.readString(S2D2C));
		capData.setS4a1q(fieldSet.readString(S4A1Q));
		capData.setS4a1s(fieldSet.readString(S4A1S));
		capData.setS4a1c(fieldSet.readString(S4A1C));
		capData.setReadyToteach(fieldSet.readString(READY_TO_TEACH));
		
		
		return capData;
	}

}
