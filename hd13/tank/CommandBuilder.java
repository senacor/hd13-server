package hd13.tank;

/**
 * Created with IntelliJ IDEA.
 * User: rwinzing
 * Date: 21.03.14
 * Time: 23:25
 * To change this template use File | Settings | File Templates.
 */
public class CommandBuilder {
    public static final String separator = ":";
    public static final String terminator = "\n";

    private CommandBuilder() {
    }

    public static String shoot(int nrOfShots, Long commandId) {
        StringBuilder builder = new StringBuilder();
        builder.append("s").append(separator).append(nrOfShots).append(separator).append(commandId).append(terminator);
        return builder.toString();
    }

    public static String left(int nrOfTurns, Long commandId) {
        StringBuilder builder = new StringBuilder();
        builder.append("l").append(separator).append(nrOfTurns).append(separator).append(commandId).append(terminator);
        return builder.toString();
    }

    public static String right(int nrOfTurns, Long commandId) {
        StringBuilder builder = new StringBuilder();
        builder.append("r").append(separator).append(nrOfTurns).append(separator).append(commandId).append(terminator);
        return builder.toString();
    }

    public static String travel(float distanceInCm, Long commandId) {
        StringBuilder builder = new StringBuilder();
        builder.append("t").append(separator).append(distanceInCm).append(separator).append(commandId).append(terminator);
        return builder.toString();
    }

    public static String measure(Long commandId) {
        StringBuilder builder = new StringBuilder();
        builder.append("m").append(separator).append(separator).append(commandId).append(terminator);
        return builder.toString();
    }

    public static String answer(Long commandId, boolean success, String value) {
        StringBuilder builder = new StringBuilder();
        builder.append("a").append(separator).append(commandId).append(separator).append(success ? 1 : 0).append(separator).append(value).append(terminator);
        return builder.toString();
    }
}
