package com.ebmwebsourcing.petals.services.cdk;

import java.util.List;

import com.ebmwebsourcing.petals.services.su.extensions.ComponentVersionDescription;
import com.ebmwebsourcing.petals.services.su.extensions.PetalsKeyWords;

public class CDK5Description extends ComponentVersionDescription {

	public CDK5Description() {
	}

	@Override
	public boolean isBc() {
		return false;
	}

	@Override
	public String getComponentName() {
		return "CDK 5.0";
	}

	@Override
	public String getComponentAlias() {
		return "CDK 5.0";
	}

	@Override
	public String getComponentAnnotation() {
		return "CDK 5.0";
	}

	@Override
	public String getProvideDescription() {
		return null;
	}

	@Override
	public String getConsumeDescription() {
		return null;
	}

	@Override
	public List<PetalsKeyWords> getKeyWords() {
		return null;
	}

	@Override
	public String getComponentVersion() {
		return "CDK 5.0";
	}

	@Override
	public boolean isProvide() {
		return false;
	}

	@Override
	public boolean isConsume() {
		return false;
	}

	@Override
	public boolean isProxy() {
		return false;
	}

}
