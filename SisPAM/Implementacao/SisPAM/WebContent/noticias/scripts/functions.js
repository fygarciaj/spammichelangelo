


	  function scrollOver()
	  {
		if (document.form.cbScroller.checked)
		    {scroller.scrollAmount='0'}
		else
		    {scroller.scrollAmount='1'}
		scroller.style.cursor='default'
	  }

	  function scrollOut()
	  {
		if (document.form.cbScroller.checked)
		    {scroller.scrollAmount='0'}
		else
		    {scroller.scrollAmount='3'}
	  }

	  function checkSBox(box)
	  {
		SBox = eval(box);
		SBox.checked = !SBox.checked;
		scrollOut();
	  }