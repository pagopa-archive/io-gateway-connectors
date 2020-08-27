public class Importer {

    private Args args;

    public Importer(Args args){
        this.args = args;
    }

    public boolean showForm() {
        return args.getHost() == null
                || args.getPort() == null
                || args.getDatabase() == null
                || args.getSid() == null
                || args.getUser() == null
                || args.getPassword() == null;
    }

}
