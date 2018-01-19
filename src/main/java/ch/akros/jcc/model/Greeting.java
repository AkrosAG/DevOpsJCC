package ch.akros.jcc.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Greeting {

	private long id;
	private String content;

	public Greeting() {
	}

	public Greeting(final long id, final String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder() //
				.append(id) //
				.append(content) //
				.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Greeting other = (Greeting) obj;

		return new EqualsBuilder() //
				.append(id, other.id) //
				.append(content, other.content) //
				.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this) //
				.append("id", id) //
				.append("content", content) //
				.toString();
	}
}
