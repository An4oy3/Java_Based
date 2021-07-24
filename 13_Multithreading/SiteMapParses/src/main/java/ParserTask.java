import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ParserTask extends RecursiveTask<List<String>> {
    private final Parser parser;

    public ParserTask(Parser parser){
        this.parser = parser;
    }

    @Override
    protected List<String> compute() {
        List<String> res = parser.getUrls();
        List<ParserTask> subTasks = new LinkedList<>();

        for (String child : parser.getUrls()) {
            ParserTask task = new ParserTask(new Parser(child));
            task.fork();
            subTasks.add(task);
        }

        for (ParserTask task : subTasks) {
            res.addAll(task.join());
        }
        System.out.println(res.size());
        return res;
    }
}
