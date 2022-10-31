package Code.src.Interface;

import Code.src.Domain.Controllers.DomainController;

public class PresentationController {
    private DomainController ctrlDomain;

    /**
     * @brief Default create of PresentationController
     */
    public PresentationController() {
        ctrlDomain = new DomainController();
    }
}
