/*
 *  Copyright 2010 tom.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package com.sampleapp.wicket.pages;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 *
 * @author tom
 */
public abstract class BasePage extends WebPage {

	protected FeedbackPanel fp;

    public BasePage() {
        super ();

		add( new MainMenuPanel("menu"));

		fp= new FeedbackPanel("feedback");

		fp.setOutputMarkupId(true);

		add(fp);

    }

	public void infoPrint(String str) {
		info(str);
		System.out.println(str);
	}

    public BasePage(PageParameters params) {
        //TODO:  process page parameters
    }



}


