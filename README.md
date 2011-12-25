<p>Slim and Simple MVP framework for GWT</p>
<p>I hope can make every thing become simple for you</p>
<br>
<h2>SMVP4G Feature</h2>
<p>Support MVP</p>
<p>Easy Using I18n</p>
<p>Support set user permission for views</p>
<p>Support set user permission for each filed in a view</p>
<p>Support Reflection</p>
<p>Support Aop</p>
<br>
<h2>Example</h2>
<p><b>View</b></p>
<pre>
	@View
	public class TestView extends AbstractView {
	    @Override
	    protected void initializeView() {
	        setWidget(new Label("Main Test View"));
	    }
	}
</pre>
<br>
<p><b>Presenter</b></p>
<pre>
	@Presenter(view = TestView.class, place = TestPlace.class)
	public class TestPresenter extends AbstractPresenter<TestView> {
	    @Override
	    public void onActivate() {
	        view.show();
	    }
	}
</pre>
<br>
<p><b>Place</b></p>
<pre>
	@Place
	public class TestPlace extends AbstractPlace {
	}
</pre>