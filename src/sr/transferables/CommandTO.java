package sr.transferables;

import java.util.HashMap;
import java.util.Map;

public class CommandTO {
    private int method;
    private Map<String, String> parameters;
    private String data;

    public CommandTO() {
        parameters = new HashMap<>();
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getParameter(String name) {
        String ret = parameters.get(name);
        if (ret == null) {
            throw new RuntimeException("Parameter " + name + " not found");
        }
        return ret;
    }

    public long getLong(String name) {
        return Long.parseLong(parameters.get(name));
    }

    public void setLong(String name, long value) {
        parameters.put(name, Long.toString(value));
    }
    
    public int getInt(String name) {
        return Integer.parseInt(parameters.get(name));
    }
    
    public void setInt(String name, int value) {
        parameters.put(name, Integer.toString(value));
    }

    public float getFloat(String name) {
        return Float.parseFloat(parameters.get(name));
    }
    
    public void setFloat(String name, float value) {
        parameters.put(name, Float.toString(value));
    }

    public boolean getBoolean(String name) {
        return Boolean.parseBoolean(parameters.get(name));
    }

    public void setBoolean(String name, boolean value) {
        parameters.put(name, Boolean.toString(value));
    }
    
    public double getDouble(String name) {
        return Double.parseDouble(parameters.get(name));
    }
    
    public void setDouble(String name, double value) {
        parameters.put(name, Double.toString(value));
    }
    
    public String getString(String name) {
        return parameters.get(name);
    }
    
    public void setString(String name, String value) {
        parameters.put(name, value);
    }

	@Override
	public String toString() {
		return "CommandTO [method=" + method + ", parameters=" + parameters.toString() + ", data=" + data + "]";
	}
    
    
}
