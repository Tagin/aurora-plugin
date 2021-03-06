package aurora.plugin.entity.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

public class Record implements IEntityConst {
	private HashMap<String, Object> map = new HashMap<String, Object>();
	PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	public Record() {
		setForInsert(true);
		setForUpdate(true);
	}

	public final Object get(String key) {
		if (key != null)
			key = key.toLowerCase();
		return map.get(key);
	}

	public final void put(String key, Object value) {
		put(key, value, true);
	}

	public final void put(String key, Object value, boolean notify) {
		String okey = key;
		if (key != null)
			key = key.toLowerCase();
		Object old = map.get(key);
		map.put(key, value);
		if (notify && !eq(old, value))
			firePropertyChange(okey, old, value);
	}

	protected boolean eq(Object o1, Object o2) {
		if (o1 == null)
			return o2 == null;
		return o1.equals(o2);
	}

	public String getString(String key) {
		Object obj = get(key);
		if (obj == null)
			return null;
		return obj.toString();
	}

	public String getStringNotNull(String key) {
		String value = getString(key);
		return value == null ? "" : value;
	}

	public int getNum() {
		return getInt(COLUMN_NUM);
	}

	public void setNum(int num) {
		put(COLUMN_NUM, num, false);
	}

	public String getPrompt() {
		return getString(COLUMN_PROMPT);
	}

	public void setPrompt(String prompt) {
		put(COLUMN_PROMPT, prompt);
	}

	public String getOptions() {
		return getStringNotNull(COLUMN_OPTIONS);
	}

	public void setOptions(String str) {
		put(COLUMN_OPTIONS, str);
	}

	public Integer getInt(String key) {
		Object obj = get(key);
		if (obj == null)
			return 0;
		else if (obj instanceof String)
			return Integer.parseInt((String) obj);
		return ((Integer) obj);
	}

	public boolean getBoolean(String key) {
		Object obj = get(key);
		if (obj == null)
			return false;
		else if (obj instanceof Boolean) {
			return (Boolean) obj;
		} else if (obj instanceof String)
			return Boolean.parseBoolean((String) obj);
		return false;
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		listeners.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		listeners.removePropertyChangeListener(l);
	}

	protected void firePropertyChange(String prop, Object old, Object newValue) {
		listeners.firePropertyChange(prop, old, newValue);
	}

	@SuppressWarnings("unchecked")
	public Record clone() {
		Record r = new Record();
		r.map = (HashMap<String, Object>) this.map.clone();
		return r;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(1000);
		for (String key : TABLE_COLUMN_PROPERTIES) {
			if (key.length() == 0)
				continue;
			sb.append(key);
			sb.append(" = ");
			sb.append(get(key));
			sb.append(" ;  ");
		}
		return sb.toString();
	}

	public String getName() {
		return getStringNotNull(COLUMN_NAME);
	}

	public void setName(String name) {
		put(COLUMN_NAME, name);
	}

	public String getType() {
		return getStringNotNull(COLUMN_TYPE);
	}

	public void setType(String type) {
		put(COLUMN_TYPE, type);
	}

	public String getEditor() {
		return getStringNotNull(COLUMN_EDITOR);
	}

	public void setEditor(String editor) {
		put(COLUMN_EDITOR, editor);
	}

	public boolean isForUpdate() {
		return getBoolean(FOR_UPDATE);
	}

	public void setForUpdate(boolean update) {
		put(FOR_UPDATE, update);
	}

	public boolean isForInsert() {
		return getBoolean(FOR_INSERT);
	}

	public void setForInsert(boolean insert) {
		put(FOR_INSERT, insert);
	}

	public boolean isForDisplay() {
		return getBoolean(FOR_DISPLAY);
	}

	public void setForDisplay(boolean display) {
		put(FOR_DISPLAY, display);
	}

	public boolean isForQuery() {
		return getBoolean(FOR_QUERY);
	}

	public void setForQuery(boolean query) {
		put(FOR_QUERY, query);
	}

	public boolean isForLov() {
		return getBoolean(FOR_LOV);
	}

	public void setForLov(boolean lov) {
		put(FOR_LOV, lov);
	}

	public String getUpdateExpression() {
		return getStringNotNull(UPDATE_EXPRESSION);
	}

	public void setUpdateExpression(String exp) {
		put(UPDATE_EXPRESSION, exp);
	}

	public String getInsertExpression() {
		return getStringNotNull(INSERT_EXPRESSION);
	}

	public void setInsertExpression(String exp) {
		put(INSERT_EXPRESSION, exp);
	}
}