package com.github.andbed.cleanarch.eventtype.infrastructure.repository.db.jpa;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.github.andbed.cleanarch.eventtype.core.entity.EventAttribute;
import com.github.andbed.cleanarch.eventtype.core.entity.EventType;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "eventtypes")
public class EventTypeJPA extends EventType {

	@Id
	Long id;

	String name;

	@Column(name = "attributes")
	String commaSeparatedAttributes;

	boolean displayed;

	@Override
	public List<EventAttribute> getAttributes() {
		return createAttributesFromString(commaSeparatedAttributes);
	}

	private List<EventAttribute> createAttributesFromString(String atts) {
		return newArrayList();
	}

}
